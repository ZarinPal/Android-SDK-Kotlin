package com.example.zarinpal.data.remote

/**
 * This object contains constants and functions related to the HTTP routes for interacting with the ZarinPal payment gateway.
 *
 * @property BASE_URL The base URL for the live ZarinPal payment gateway.
 * @property BASE_URL_SANDBOX The base URL for the sandbox (test) environment of the ZarinPal payment gateway.
 * @property START_PAY_URL The URL endpoint for starting a payment.
 * @property BASE_URL_GRAPH The base URL for the GraphQL API of ZarinPal.
 * @property PAYMENT The endpoint for creating a payment request.
 * @property PAYMENT_VERIFY The endpoint for verifying a payment.
 * @property PAYMENT_INQUIRY The endpoint for inquiring about a payment.
 * @property PAYMENT_UN_VERIFIED The endpoint for querying unverified payments.
 * @property PAYMENT_REVERSE The endpoint for reversing a payment.
 */
object HttpRoutes {
    private const val BASE_URL = "https://payment.zarinpal.com"
    private const val BASE_URL_SANDBOX = "https://sandbox.zarinpal.com"
    private const val START_PAY_URL = "/pg/StartPay/"

    const val BASE_URL_GRAPH = "https://next.zarinpal.com/api/v4/graphql"

    private const val PAYMENT = "/pg/v4/payment/request.json"
    private const val PAYMENT_VERIFY = "/pg/v4/payment/verify.json"
    private const val PAYMENT_INQUIRY = "/pg/v4/payment/inquiry.json"
    private const val PAYMENT_UN_VERIFIED = "/pg/v4/payment/unVerified.json"
    private const val PAYMENT_REVERSE = "/pg/v4/payment/reverse.json"
    private const val PAYMENT_FEE = "/pg/v4/payment/feeCalculation.json"

    /**
     * Generates the URL for creating a payment request.
     *
     * @param sandBox A boolean indicating whether to use the sandbox (test) environment.
     * @return The full URL for the payment request.
     */
    fun createPayment(sandBox: Boolean): String {
        return (if (sandBox) BASE_URL_SANDBOX else BASE_URL) + PAYMENT
    }

    /**
     * Generates the URL for verifying a payment.
     *
     * @param sandBox A boolean indicating whether to use the sandbox (test) environment.
     * @return The full URL for the payment verification request.
     */
    fun paymentVerify(sandBox: Boolean): String {
        return (if (sandBox) BASE_URL_SANDBOX else BASE_URL) + PAYMENT_VERIFY
    }

    /**
     * Generates the URL for inquiring about a payment.
     *
     * @param sandBox A boolean indicating whether to use the sandbox (test) environment.
     * @return The full URL for the payment inquiry request.
     */
    fun paymentInquiry(sandBox: Boolean): String {
        return (if (sandBox) BASE_URL_SANDBOX else BASE_URL) + PAYMENT_INQUIRY
    }

    /**
     * Generates the URL for querying unverified payments.
     *
     * @param sandBox A boolean indicating whether to use the sandbox (test) environment.
     * @return The full URL for the unverified payment query request.
     */
    fun paymentUnVerified(sandBox: Boolean): String {
        return (if (sandBox) BASE_URL_SANDBOX else BASE_URL) + PAYMENT_UN_VERIFIED
    }

    /**
     * Generates the URL for reversing a payment.
     *
     * @param sandBox A boolean indicating whether to use the sandbox (test) environment.
     * @return The full URL for the payment reversal request.
     */
    fun paymentReverse(sandBox: Boolean): String {
        return (if (sandBox) BASE_URL_SANDBOX else BASE_URL) + PAYMENT_REVERSE
    }

    /**
     * Generates the URL for redirecting a user to the payment page with the provided authority.
     *
     * @param authority The payment authority returned by the payment gateway.
     * @param sandBox A boolean indicating whether to use the sandbox (test) environment.
     * @return The full URL for redirecting the user to the payment page.
     */
    fun getRedirectUrl(authority: String, sandBox: Boolean): String {
        if ((authority ?: "").isEmpty()) return ""
        return (if (sandBox) BASE_URL_SANDBOX else BASE_URL) + START_PAY_URL + authority
    }


    /**
     * Generates the URL for fee of  a payment.
     *
     * @return The full URL for the payment fee request.
     */
    fun getPaymentFee(): String {
        return (  BASE_URL) + PAYMENT_FEE
    }



}