package com.example.zarinpal

import com.example.zarinpal.data.remote.HttpRoutes
import com.example.zarinpal.data.remote.PaymentService
import com.example.zarinpal.data.remote.dto.Config
import com.example.zarinpal.data.remote.dto.create.CreatePaymentDataResponse
import com.example.zarinpal.data.remote.dto.create.CreatePaymentRequest
import com.example.zarinpal.data.remote.dto.fee.PaymentFeeRequest
import com.example.zarinpal.data.remote.dto.fee.PaymentFeeResponse
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
import com.example.zarinpal.utils.Validator

/**
 * ZarinPal class handles all interactions with the ZarinPal API.
 * Provides methods for creating payments, verifying payments, refunds, reversals, and more.
 */
class ZarinPal(config: Config) {

    // Instance of PaymentService used for API calls
    private val service = PaymentService.create(config)

    // Configuration object containing sandbox mode and other settings
    private val config = config

    /**
     * Creates a new payment and returns the response.
     * @param paymentRequest Request data for creating the payment.
     * @param redirectUrl Callback function to handle the payment gateway URL and status.
     * @return [CreatePaymentDataResponse] containing details of the created payment.
     */
    suspend fun createPayment(
        paymentRequest: CreatePaymentRequest,
        redirectUrl: (paymentGatewayUri: String, status: Int) -> Unit
    ): CreatePaymentDataResponse? {
        Validator.validateMerchantId(paymentRequest.merchantId ?: config.merchantId)
        Validator.validateCallbackUrl(paymentRequest.callbackUrl)
        Validator.validateAmount(paymentRequest.amount)
        Validator.validateMobile(paymentRequest.metadata?.mobile)
        Validator.validateEmail(paymentRequest.metadata?.email)
        Validator.validateCardPan(paymentRequest.cardPan)

        val paymentResponse =
            service.createPayment(paymentRequest)

        val paymentGatewayUri = HttpRoutes.getRedirectUrl(
            sandBox = paymentRequest.sandBox ?: config.sandBox,
            authority = paymentResponse?.authority ?: ""
        )

        redirectUrl(paymentGatewayUri, paymentResponse?.code ?: 0)

        return paymentResponse
    }

    /**
     * Verifies a payment using the authority code.
     * @param paymentVerifyRequest Request data for verifying the payment.
     * @return [PaymentVerificationDataResponse] containing verification details.
     */
    suspend fun paymentVerify(
        paymentVerifyRequest: PaymentVerifyRequest
    ): PaymentVerificationDataResponse? {
        Validator.validateMerchantId(paymentVerifyRequest.merchantId ?: config.merchantId)
        Validator.validateAuthority(paymentVerifyRequest.authority)
        Validator.validateAmount(paymentVerifyRequest.amount)

        return service.paymentVerify(paymentVerifyRequest)
    }

    /**
     * Inquires about a payment status.
     * @param paymentInquiryRequest Request data for the payment inquiry.
     * @return [PaymentInquiryDataResponse] containing inquiry details.
     */
    suspend fun paymentInquiry(
        paymentInquiryRequest: PaymentInquiryRequest
    ): PaymentInquiryDataResponse? {
        Validator.validateMerchantId(paymentInquiryRequest.merchantId ?: config.merchantId)
        Validator.validateAuthority(paymentInquiryRequest.authority)

        return service.paymentInquiry(paymentInquiryRequest)
    }


    /**
     * Retrieves unverified payments.
     * @param paymentUnVerifiedRequest Optional request data for retrieving unverified payments.
     * @return [PaymentUnVerifiedDataResponse] containing unverified payment details.
     */
    suspend fun paymentUnVerified(
        paymentUnVerifiedRequest: PaymentUnVerifiedRequest = PaymentUnVerifiedRequest()
    ): PaymentUnVerifiedDataResponse? {
        Validator.validateMerchantId(paymentUnVerifiedRequest.merchantId ?: config.merchantId)

        return service.paymentUnVerified(paymentUnVerifiedRequest)
    }

    /**
     * Retrieves unverified payments.
     * @param paymentUnVerifiedRequest Optional request data for retrieving unverified payments.
     * @return [PaymentUnVerifiedDataResponse] containing unverified payment details.
     */
    suspend fun paymentReverse(
        paymentReverseRequest: PaymentReverseRequest
    ): PaymentReverseDataResponse? {
        Validator.validateMerchantId(paymentReverseRequest.merchantId ?: config.merchantId)
        Validator.validateAuthority(paymentReverseRequest.authority)

        return service.paymentReverse(paymentReverseRequest)
    }

    /**
     * Retrieves transaction history.
     * @param transactionRequest Request data for fetching transactions.
     * @return A list of [Session] objects containing transaction details.
     */
    suspend fun getTransactions(
        transactionRequest: TransactionRequest
    ): List<Session>? {
        Validator.validateTerminalId(transactionRequest.terminalId)
        Validator.validateLimit(transactionRequest.limit)
        Validator.validateOffset(transactionRequest.offset)

        return service.getTransactions(transactionRequest)
    }

    /**
     * Processes a payment refund.
     * @param paymentRefundRequest Request data for refunding the payment.
     * @return [PaymentRefundResponse] containing refund details.
     */
    suspend fun paymentRefund(
        paymentRefundRequest: PaymentRefundRequest
    ): PaymentRefundResponse? {
        Validator.validateSessionId(paymentRefundRequest.sessionId)
        Validator.validateAmount(paymentRefundRequest.amount, minAmount = 20_000)

        return service.paymentRefund(paymentRefundRequest)
    }



    /**
     * Retrieves the payment fee details by delegating the request to the PaymentService.
     *
     * @param paymentFeeRequest Contains the necessary information for calculating the payment fee.
     * @return A PaymentFeeResponse with fee details, or null if the service call fails.
     */
    suspend fun getPaymentFee(
        paymentFeeRequest: PaymentFeeRequest
    ): PaymentFeeResponse? {
        return service.getPaymentFee(paymentFeeRequest)
    }



}