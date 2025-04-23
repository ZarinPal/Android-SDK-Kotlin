package com.example.zarinpal.data.remote.dto.reverse

import androidx.annotation.Keep
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/**
 * Represents the response received after a payment reversal attempt.
 *
 * @property data Contains details of the reversal result.
 * @property errors Optional error information returned by the gateway.
 */
@Keep
@Serializable
data class PaymentReverseResponse(
    val data: PaymentReverseDataResponse,
    val errors: JsonElement?
)

/**
 * Details the outcome of the payment reversal.
 *
 * @property code Status code representing the result.
 * @property message Description of the result or any error encountered.
 */
@Keep
@Serializable
data class PaymentReverseDataResponse(
    val code: Int,
    val message: String
)
