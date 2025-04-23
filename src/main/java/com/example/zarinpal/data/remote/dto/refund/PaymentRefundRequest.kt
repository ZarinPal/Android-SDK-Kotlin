package com.example.zarinpal.data.remote.dto.refund

import androidx.annotation.Keep
import com.example.zarinpal.data.remote.enum.MethodEnum
import com.example.zarinpal.data.remote.enum.ReasonEnum
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Request data model for initiating a payment refund.
 *
 * @property sessionId Unique identifier for the payment session being refunded.
 * @property description Description of the refund (e.g., "Refund for Order #1234").
 * @property method Method used for the refund (e.g., "credit", "bank transfer").
 * @property reason Reason for the refund (e.g., "Product returned", "Payment error").
 * @property amount Amount to be refunded in the smallest currency unit (e.g., IRR).
 * @property token Optional security token.
 */
@Keep
@Serializable
data class PaymentRefundRequest(
    @SerialName("session_id")
    val sessionId: String,
    val description: String,
    val method: MethodEnum,
    val reason: ReasonEnum,
    val amount: Int,
    val token: String? = null
)
