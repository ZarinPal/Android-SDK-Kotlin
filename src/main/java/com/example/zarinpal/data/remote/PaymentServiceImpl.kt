package com.example.zarinpal.data.remote

import com.example.zarinpal.data.remote.dto.Config
import com.example.zarinpal.data.remote.dto.create.*
import com.example.zarinpal.data.remote.dto.inquiry.*
import com.example.zarinpal.data.remote.dto.refund.*
import com.example.zarinpal.data.remote.dto.reverse.*
import com.example.zarinpal.data.remote.dto.transaction.*
import com.example.zarinpal.data.remote.dto.unVerified.*
import com.example.zarinpal.data.remote.dto.verification.*
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.*
import io.ktor.client.statement.readText
import org.json.JSONArray
import org.json.JSONObject

class PaymentServiceImpl(
    private val client: HttpClient,
    private val config: Config
) : PaymentService {

    override suspend fun createPayment(request: CreatePaymentRequest): CreatePaymentDataResponse? =
        handleRequest {
            client.post<CreatePaymentResponse> {
                url(HttpRoutes.createPayment(request.sandBox ?: config.sandBox))
                setBody(request.copyWithConfig(config))
            }.data
        }

    override suspend fun paymentVerify(request: PaymentVerifyRequest): PaymentVerificationDataResponse? =
        handleRequest {
            client.post<PaymentVerificationResponse> {
                url(HttpRoutes.paymentVerify(request.sandBox ?: config.sandBox))
                setBody(request.copyWithConfig(config))
            }.data
        }

    override suspend fun paymentInquiry(request: PaymentInquiryRequest): PaymentInquiryDataResponse? =
        handleRequest {
            client.post<PaymentInquiryResponse> {
                url(HttpRoutes.paymentInquiry(request.sandBox ?: config.sandBox))
                setBody(request.copyWithConfig(config))
            }.data
        }

    override suspend fun paymentUnVerified(request: PaymentUnVerifiedRequest): PaymentUnVerifiedDataResponse? =
        handleRequest {
            client.post<PaymentUnVerifiedResponse> {
                url(HttpRoutes.paymentUnVerified(request.sandBox ?: config.sandBox))
                setBody(request.copyWithConfig(config))
            }.data
        }

    override suspend fun paymentReverse(request: PaymentReverseRequest): PaymentReverseDataResponse? =
        handleRequest {
            client.post<PaymentReverseResponse> {
                url(HttpRoutes.paymentReverse(request.sandBox ?: config.sandBox))
                setBody(request.copyWithConfig(config))
            }.data
        }

    override suspend fun getTransactions(request: TransactionRequest): List<Session>? =
        handleRequest {
            val query = """
                query Sessions(\$terminal_id: ID!, \$filter: FilterEnum, \$id: ID, \$reference_id: String, \$rrn: String, \$card_pan: String, \$email: String, \$mobile: CellNumber, \$description: String, \$limit: Int, \$offset: Int) {
                    Session(terminal_id: \$terminal_id, filter: \$filter, id: \$id, reference_id: \$reference_id, rrn: \$rrn, card_pan: \$card_pan, email: \$email, mobile: \$mobile, description: \$description, limit: \$limit, offset: \$offset) {
                        id
                        status
                        amount
                        description
                        created_at
                    }
                }
            """.trimIndent()

            client.post<TransactionResponse> {
                url(HttpRoutes.BASE_URL_GRAPH)
                header("Authorization", "Bearer ${request.token ?: config.token}")
                setBody(GraphTransactionModel(query, request))
            }.data?.session
        }

    override suspend fun paymentRefund(request: PaymentRefundRequest): PaymentRefundResponse? =
        handleRequest {
            val query = """
                mutation AddRefund(\$session_id: ID!, \$amount: BigInteger!, \$description: String, \$method: InstantPayoutActionTypeEnum, \$reason: RefundReasonEnum) {
                    resource: AddRefund(session_id: \$session_id, amount: \$amount, description: \$description, method: \$method, reason: \$reason) {
                        terminal_id
                        id
                        amount
                        timeline {
                            refund_amount
                            refund_time
                            refund_status
                        }
                    }
                }
            """.trimIndent()

            client.post<PaymentRefundResponseModel> {
                url(HttpRoutes.BASE_URL_GRAPH)
                header("Authorization", "Bearer ${request.token ?: config.token}")
                setBody(GraphRefundModel(query, request))
            }.data?.resource
        }

    private suspend fun <T> handleRequest(request: suspend () -> T): T =
        try {
            request()
        } catch (e: RedirectResponseException) {
            throw Exception(extractError(e.response.readText()) ?: e.response.status.description)
        } catch (e: ClientRequestException) {
            throw Exception(extractError(e.response.readText()) ?: e.response.status.description)
        } catch (e: ServerResponseException) {
            throw Exception(extractError(e.response.readText()) ?: e.response.status.description)
        } catch (e: Exception) {
            throw e
        }

    private fun extractError(json: String?): String? {
        if (json.isNullOrBlank()) return null

        return try {
            val obj = JSONObject(json)
            when (val errors = obj.opt("errors")) {
                is JSONObject -> errors.optString("fa_message") ?: errors.optString("message")
                is JSONArray -> (0 until errors.length())
                    .mapNotNull { errors.optJSONObject(it) }
                    .flatMap { listOfNotNull(
                        it.optString("fa_message"),
                        it.optString("message"),
                        it.optJSONArray("validation")?.optJSONObject(0)?.optString("fa_message"),
                        it.optJSONArray("validation")?.optJSONObject(0)?.optString("message")
                    ) }
                    .firstOrNull()
                else -> obj.optString("fa_message") ?: obj.optString("message")
            }
        } catch (_: Exception) {
            null
        }
    }
}
 
