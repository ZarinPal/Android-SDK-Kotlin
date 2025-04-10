package com.example.zarinpal.data.remote

/**
 * Utility object for building HTTP routes related to the ZarinPal payment gateway.
 */
object HttpRoutes {

    private const val BASE_URL = "https://payment.zarinpal.com"
    private const val BASE_URL_SANDBOX = "https://sandbox.zarinpal.com"
    private const val START_PAY_URL = "/pg/StartPay/"

    const val BASE_URL_GRAPH = "https://next.zarinpal.com/api/v4/graphql"

    private const val PAYMENT = "/pg/v4/payment/request.json"
    private const val PAYMENT_VERIFY = "/pg/v4/payment/verify.json"
    private const val PAYMENT_INQUIRY = "/pg/v4/payment/inquiry.json"
    private const val PAYMENT_UNVERIFIED = "/pg/v4/payment/unVerified.json"
    private const val PAYMENT_REVERSE = "/pg/v4/payment/reverse.json"

    private fun baseUrl(sandbox: Boolean): String =
        if (sandbox) BASE_URL_SANDBOX else BASE_URL

    fun createPayment(sandbox: Boolean): String =
        baseUrl(sandbox) + PAYMENT

    fun paymentVerify(sandbox: Boolean): String =
        baseUrl(sandbox) + PAYMENT_VERIFY

    fun paymentInquiry(sandbox: Boolean): String =
        baseUrl(sandbox) + PAYMENT_INQUIRY

    fun paymentUnverified(sandbox: Boolean): String =
        baseUrl(sandbox) + PAYMENT_UNVERIFIED

    fun paymentReverse(sandbox: Boolean): String =
        baseUrl(sandbox) + PAYMENT_REVERSE

    fun getRedirectUrl(authority: String, sandbox: Boolean): String =
        if (authority.isBlank()) "" else baseUrl(sandbox) + START_PAY_URL + authority
}
