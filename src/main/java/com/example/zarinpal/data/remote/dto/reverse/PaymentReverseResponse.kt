package com.example.zarinpal.data.remote.dto.reverse

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/**
 * Represents the response data returned after reversing a payment.
 *
 * @property data Contains the details of the payment reversal response.
 * @property errors If present, contains any errors returned by the payment gateway.
 */
@Keep
@Serializable
data class PaymentReverseResponse(
    val data: PaymentReverseDataResponse,
    val errors: JsonElement?
)

/**
 * Contains the data returned from the payment reversal process.
 *
 * @property code A numeric code indicating the result of the reversal.
 * @property message A descriptive message explaining the result or error.
 */
@Keep
@Serializable
data class PaymentReverseDataResponse(
    val code: Int,
    val message: String,
)
