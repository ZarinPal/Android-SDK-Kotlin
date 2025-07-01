package com.example.zarinpal.data.remote.dto.verification

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/**
 * Represents the response data returned when verifying a payment.
 *
 * @property data Contains the details of the payment verification response.
 * @property errors If present, contains any errors returned by the payment gateway.
 */
@Keep
@Serializable
data class PaymentVerificationResponse(
    val data: PaymentVerificationDataResponse,
    val errors: JsonElement?
)

/**
 * Contains the data returned from the payment verification request.
 *
 * @property wages A list of wages associated with the payment (nullable).
 * @property code A numeric code indicating the result of the request.
 * @property message A descriptive message explaining the result or error.
 * @property cardHash The hash of the card used for the payment.
 * @property cardPan The primary account number (PAN) of the card used for the payment.
 * @property refId The reference ID for the transaction (nullable).
 * @property feeType The type of fee applied to the payment.
 * @property fee The amount of the fee applied to the payment.
 * @property shaparakFee The fee imposed by the Shaparak payment system.
 * @property orderId The unique identifier for the order associated with the payment (nullable).
 */
@Keep
@Serializable
data class PaymentVerificationDataResponse(
    val wages: List<String>? = emptyList(),
    val code: Int,
    val message: String,
    @SerialName("card_hash")
    val cardHash: String,
    @SerialName("card_pan")
    val cardPan: String,
    @SerialName("ref_id")
    val refId: Long?,
    @SerialName("fee_type")
    val feeType: String?,
    val fee: Int,
    @SerialName("shaparak_fee")
    val shaparakFee: Int,
    @SerialName("order_id")
    val orderId: String? = null
)
