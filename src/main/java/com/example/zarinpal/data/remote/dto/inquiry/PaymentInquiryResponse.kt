package com.example.zarinpal.data.remote.dto.inquiry

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/**
 * Represents the response data returned after inquiring about a payment.
 *
 * @property data Contains the details of the payment inquiry response.
 * @property errors If present, contains any errors returned by the payment gateway.
 */
@Keep
@Serializable
data class PaymentInquiryResponse(
    val data: PaymentInquiryDataResponse,
    val errors: JsonElement?
)

/**
 * Represents the data returned from the payment inquiry.
 *
 * @property status The status of the payment inquiry (e.g., "success", "failed").
 * @property code A numeric code indicating the result of the inquiry.
 * @property message A descriptive message explaining the result or error.
 */
@Keep
@Serializable
data class PaymentInquiryDataResponse(
    val status:String,
    val code: Int,
    val message: String,
)
