package com.example.zarinpal

import com.example.zarinpal.data.remote.HttpRoutes
import com.example.zarinpal.data.remote.PaymentService
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
import com.example.zarinpal.utils.Validator

/**
 * ZarinPal class handles all interactions with the ZarinPal API.
 * Provides methods for creating payments, verifying payments, refunds, reversals, and more.
 */
class ZarinPal(private val config: Config) {

    private val service = PaymentService.create(config)

    suspend fun createPayment(
        paymentRequest: CreatePaymentRequest,
        redirectUrl: (paymentGatewayUri: String, status: Int) -> Unit
    ): CreatePaymentDataResponse? {
        with(Validator) {
            validateMerchantId(paymentRequest.merchantId ?: config.merchantId)
            validateCallbackUrl(paymentRequest.callbackUrl)
            validateAmount(paymentRequest.amount)
            validateMobile(paymentRequest.metadata?.mobile)
            validateEmail(paymentRequest.metadata?.email)
            validateCardPan(paymentRequest.cardPan)
        }

        val paymentResponse = service.createPayment(paymentRequest)

        val paymentGatewayUri = HttpRoutes.getRedirectUrl(
            sandBox = paymentRequest.sandBox ?: config.sandBox,
            authority = paymentResponse?.authority.orEmpty()
        )

        redirectUrl(paymentGatewayUri, paymentResponse?.code ?: 0)

        return paymentResponse
    }

    suspend fun paymentVerify(paymentVerifyRequest: PaymentVerifyRequest): PaymentVerificationDataResponse? {
        with(Validator) {
            validateMerchantId(paymentVerifyRequest.merchantId ?: config.merchantId)
            validateAuthority(paymentVerifyRequest.authority)
            validateAmount(paymentVerifyRequest.amount)
        }

        return service.paymentVerify(paymentVerifyRequest)
    }

    suspend fun paymentInquiry(paymentInquiryRequest: PaymentInquiryRequest): PaymentInquiryDataResponse? {
        with(Validator) {
            validateMerchantId(paymentInquiryRequest.merchantId ?: config.merchantId)
            validateAuthority(paymentInquiryRequest.authority)
        }

        return service.paymentInquiry(paymentInquiryRequest)
    }

    suspend fun paymentUnVerified(
        paymentUnVerifiedRequest: PaymentUnVerifiedRequest = PaymentUnVerifiedRequest()
    ): PaymentUnVerifiedDataResponse? {
        Validator.validateMerchantId(paymentUnVerifiedRequest.merchantId ?: config.merchantId)
        return service.paymentUnVerified(paymentUnVerifiedRequest)
    }

    suspend fun paymentReverse(paymentReverseRequest: PaymentReverseRequest): PaymentReverseDataResponse? {
        with(Validator) {
            validateMerchantId(paymentReverseRequest.merchantId ?: config.merchantId)
            validateAuthority(paymentReverseRequest.authority)
        }

        return service.paymentReverse(paymentReverseRequest)
    }

    suspend fun getTransactions(transactionRequest: TransactionRequest): List<Session>? {
        with(Validator) {
            validateTerminalId(transactionRequest.terminalId)
            validateLimit(transactionRequest.limit)
            validateOffset(transactionRequest.offset)
        }

        return service.getTransactions(transactionRequest)
    }

    suspend fun paymentRefund(paymentRefundRequest: PaymentRefundRequest): PaymentRefundResponse? {
        with(Validator) {
            validateSessionId(paymentRefundRequest.sessionId)
            validateAmount(paymentRefundRequest.amount, minAmount = 20_000)
        }

        return service.paymentRefund(paymentRefundRequest)
    }
}
 
