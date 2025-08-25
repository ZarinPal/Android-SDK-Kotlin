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
import io.ktor.client.engine.android.Android
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import android.os.Build
import androidx.core.os.BuildCompat
import androidx.core.os.BundleCompat
import com.example.zarinpal.data.remote.dto.fee.PaymentFeeRequest
import com.example.zarinpal.data.remote.dto.fee.PaymentFeeResponse


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


    /**
     * Retrieves the fee for a payment.
     *
     * @param paymentFeeRequest The request object containing details for fee calculation.
     * @return A PaymentFeeResponse object with fee details, or null if the request fails.
     */
    suspend fun getPaymentFee(paymentFeeRequest: PaymentFeeRequest): PaymentFeeResponse?

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
                client = HttpClient(Android) {
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
                        header("User-Agent", "ZarinPalSdk/v.1.1.2 (android kotlin)")
                        header("Content-Type", "application/json")
                        header("Package-Name", config.packageName)
                        contentType(ContentType.Application.Json)
                    }
                },
            )
        }
    }
}