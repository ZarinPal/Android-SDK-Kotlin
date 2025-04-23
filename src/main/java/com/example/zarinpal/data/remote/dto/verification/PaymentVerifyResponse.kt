package com.example.zarinpal.data.remote.dto.verification

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/**
 * Response model for payment verification.
 *
 * @property data Detailed response of the verified payment.
 * @property errors Optional error information from the payment gateway.
 */
@Keep
@Serializable
data class PaymentVerificationResponse(
    val data: PaymentVerificationDataResponse,
    val errors: JsonElement?
)

/**
 * Contains payment verification result details.
 *
 * @property wages Optional list of wages associated with the payment.
 * @property code Numeric status code representing the outcome.
 * @property message Description of the result or any error.
 * @property cardHash Hash of the card used in the transaction.
 * @property cardPan Primary account number (PAN) of the card.
 * @property refId Optional reference ID for the transaction.
 * @property feeType Optional type of fee applied.
 * @property fee Amount of the applied fee.
 * @property shaparakFee Fee charged by the Shaparak system.
 * @property orderId Optional identifier of the associated order.
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
