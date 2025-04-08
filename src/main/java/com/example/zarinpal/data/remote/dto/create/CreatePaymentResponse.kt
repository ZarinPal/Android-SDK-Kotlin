@file:Suppress("PLUGIN_IS_NOT_ENABLED")

package com.example.zarinpal.data.remote.dto.create

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/**
 * Represents the complete response returned by the payment creation API.
 *
 * @property data   Main response data with payment details.
 * @property errors JSON element containing error details, if any.
 */
@Keep
@Serializable
data class CreatePaymentResponse(
    val data: CreatePaymentDataResponse,
    val errors: JsonElement?
)

/**
 * Represents the response data returned after creating a payment.
 *
 * @property authority Unique ID for the payment session.
 * @property message   Status message of the payment creation.
 * @property feeType   Responsible party for the payment fee.
 * @property fee       Fee amount associated with the payment.
 * @property code      Status code (e.g., 100 for success).
 */
@Keep
@Serializable
data class CreatePaymentDataResponse(
    val authority: String,
    val message: String,

    @SerialName("fee_type")
    val feeType: String?,

    val fee: Int,
    val code: Int
)
