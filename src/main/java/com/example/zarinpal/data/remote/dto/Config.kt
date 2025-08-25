package com.example.zarinpal.data.remote.dto

/**
 * Defines configuration parameters for accessing the payment service.
 *
 * @property merchantId The unique identifier used to authenticate the merchant.
 * @property token Optional token used for enhanced authentication, if applicable.
 * @property sandBox Flag indicating whether sandbox (test) mode is enabled. Defaults to `false`.
 */
data class Config(
    val merchantId: String,
    val packageName:String,
    val token: String? = null,
    val sandBox: Boolean = false
)
