@file:Suppress("PLUGIN_IS_NOT_ENABLED")

package com.example.zarinpal.data.remote.dto.create

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/**
 * Represents the complete response returned by the payment creation API.
 *
 * @property data Contains the main response data ([CreatePaymentDataResponse]) with payment details.
 * @property errors A JSON element containing error details, if any, in the API response.
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
 * @property authority A unique identifier for the payment session, used for tracking.
 * @property message A message indicating the status of the payment creation process.
 * @property feeType Specifies the entity responsible for payment fees (e.g., "Merchant" or "Payer").
 * @property fee The fee amount associated with the payment.
 * @property code Status code of the payment creation (e.g., 100 for success).
 */
@Keep
@Serializable
data class CreatePaymentDataResponse(
    val authority: String,
    val message: String,
    @SerialName("fee_type")
    val feeType: String?,
    val fee: Int,
    val code: Int,
)