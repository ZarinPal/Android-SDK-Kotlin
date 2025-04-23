package com.example.zarinpal.data.remote.dto.inquiry

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/**
 * Represents the response from a payment inquiry.
 *
 * @property data Details of the payment inquiry.
 * @property errors Error information returned by the payment gateway, if any.
 */
@Keep
@Serializable
data class PaymentInquiryResponse(
    val data: PaymentInquiryDataResponse,
    val errors: JsonElement?
)

/**
 * Contains the details returned from a payment inquiry.
 *
 * @property status Status of the payment (e.g., "success", "failed").
 * @property code Numeric result code of the inquiry.
 * @property message Human-readable message about the result or error.
 */
@Keep
@Serializable
data class PaymentInquiryDataResponse(
    val status: String,
    val code: Int,
    val message: String
)
