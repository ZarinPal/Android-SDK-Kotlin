package com.example.zarinpal.data.remote.dto.refund

import androidx.annotation.Keep
import com.example.zarinpal.data.remote.enum.MethodEnum
import com.example.zarinpal.data.remote.enum.ReasonEnum
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the request data required to initiate a payment refund.
 *
 * @property sessionId The unique identifier for the payment session being refunded.
 * @property description A description of the refund (e.g., "Refund for Order #1234").
 * @property method The method used for the refund (e.g., "credit", "bank transfer").
 * @property reason The reason for the refund (e.g., "Product returned", "Payment error").
 * @property amount The amount to be refunded in the smallest currency unit (e.g., IRR).
 * @property token A token used for additional security (nullable).
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
    val token: String? = null,
)

