package com.example.zarinpal.data.remote

import com.example.zarinpal.data.remote.dto.Config
import com.example.zarinpal.data.remote.dto.create.CreatePaymentDataResponse
import com.example.zarinpal.data.remote.dto.create.CreatePaymentRequest
import com.example.zarinpal.data.remote.dto.inquiry.PaymentInquiryDataResponse
import com.example.zarinpal.data.remote.dto.inquiry.PaymentInquiryRequest
import com.example.zarinpal.data.remote.dto.refund.PaymentRefundRequest
import com.example.zarinpal.data.remote.dto.refund.PaymentRefundResponse
import com.example.zarinpal.data.remote.dto.reverse.PaymentReverseDataResponse
import com.example.zarinpal.data.remote.dto.reverse.PaymentReverseRequest
import com.example.zarinpal.data.remote.dto.transaction.Session
import com.example.zarinpal.data.remote.dto.transaction.TransactionRequest
import com.example.zarinpal.data.remote.dto.unVerified.PaymentUnVerifiedDataResponse
import com.example.zarinpal.data.remote.dto.unVerified.PaymentUnVerifiedRequest
import com.example.zarinpal.data.remote.dto.verification.PaymentVerificationDataResponse
import com.example.zarinpal.data.remote.dto.verification.PaymentVerifyRequest
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import java.util.concurrent.TimeUnit


/**
 * PaymentService interface provides functions to interact with the ZarinPal payment system.
 * Each function corresponds to a specific endpoint in the ZarinPal API.
 */
interface PaymentService {

    /**
     * Creates a payment request.
     *
     * @param paymentRequest The payment request details.
     * @return A response containing the payment data or null if the request fails.
     */
    suspend fun createPayment(paymentRequest: CreatePaymentRequest): CreatePaymentDataResponse?

    /**
     * Creates a payment request.
     *
     * @param paymentRequest The payment request details.
     * @return A response containing the payment data or null if the request fails.
     */
    suspend fun paymentVerify(paymentVerifyRequest: PaymentVerifyRequest): PaymentVerificationDataResponse?

    /**
     * Inquires about a payment.
     *
     * @param paymentInquiryRequest The request containing the payment inquiry data.
     * @return A response containing the payment inquiry data or null if the inquiry fails.
     */
    suspend fun paymentInquiry(paymentInquiryRequest: PaymentInquiryRequest): PaymentInquiryDataResponse?

    /**
     * Retrieves unverified payments.
     *
     * @param paymentUnVerifiedRequest The request containing the unverified payment data.
     * @return A response containing the unverified payment data or null if the request fails.
     */
    suspend fun paymentUnVerified(paymentUnVerifiedRequest: PaymentUnVerifiedRequest): PaymentUnVerifiedDataResponse?

    /**
     * Reverses a payment.
     *
     * @param paymentReverseRequest The request containing the payment reversal data.
     * @return A response containing the payment reversal data or null if the reversal fails.
     */
    suspend fun paymentReverse(paymentReverseRequest: PaymentReverseRequest): PaymentReverseDataResponse?

    /**
     * Retrieves a list of transactions.
     *
     * @param transactionRequest The request containing the transaction data.
     * @return A list of transactions or null if the request fails.
     */
    suspend fun getTransactions(transactionRequest: TransactionRequest): List<Session>?

    /**
     * Processes a payment refund.
     *
     * @param paymentRefundRequest The request containing the payment refund data.
     * @return A response containing the payment refund data or null if the refund fails.
     */
    suspend fun paymentRefund(paymentRefundRequest: PaymentRefundRequest): PaymentRefundResponse?

    companion object {
        /**
         * Creates an instance of PaymentService with the provided configuration.
         *
         * @param config The configuration for the service.
         * @return A new instance of PaymentService.
         */
        fun create(config: Config): PaymentService {
            return PaymentServiceImpl(
                config = config,
                client = HttpClient(OkHttp) {
                    engine {
                        config {
                            retryOnConnectionFailure(true) // Retry if connection is lost
                            connectTimeout(30, TimeUnit.SECONDS) // Time to wait for connection
                            readTimeout(30, TimeUnit.SECONDS) // Time to read response
                            writeTimeout(30, TimeUnit.SECONDS) // Time to write request
                        }
                    }
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer(
                            Json {
                                ignoreUnknownKeys = true
                            }
                        )
                    }
                    defaultRequest {
                        header("User-Agent", "ZarinPalSdk/v.1.1.1 (android kotlin)")
                        header("Content-Type", "application/json")
                        contentType(ContentType.Application.Json)
                    }
                },
            )
        }
    }
}