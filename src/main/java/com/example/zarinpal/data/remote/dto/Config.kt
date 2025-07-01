package com.example.zarinpal.data.remote.dto

/**
 * Represents the configuration settings required for interacting with the payment service.
 *
 * @property merchantId A unique identifier for the merchant, used to authenticate requests.
 * @property token An optional token for additional authentication, if required.
 * @property sandBox Specifies whether the application should operate in sandbox mode (test mode).
 *                   Defaults to `false` for production environment.
 */
data class Config(
    val merchantId: String,
    val token: String? = null,
    val sandBox :Boolean=false
)