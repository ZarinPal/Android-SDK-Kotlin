package com.example.zarinpal.data.remote.dto.refund

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the response data returned after initiating a payment refund.
 *
 * @property data Contains the details of the refund response.
 */
@Serializable
data class PaymentRefundResponseModel(
    val data: PaymentRefundDataModel
)

/**
 * Contains the data returned from the payment refund process.
 *
 * @property resource The detailed response of the refund transaction.
 */
@Serializable
data class PaymentRefundDataModel(
    val resource: PaymentRefundResponse
)

/**
 * Represents the details of the refund transaction.
 *
 * @property terminalId The terminal ID used for the refund.
 * @property id The unique identifier of the refund transaction.
 * @property amount The total amount refunded in the smallest currency unit (e.g., IRR).
 * @property timeline Contains details of the refund process, including the amount, time, and status.
 */
@Serializable
data class PaymentRefundResponse(
    @SerialName("terminal_id") val terminalId: String,
    val id: String,
    val amount: Int,
    val timeline: PaymentRefundTimeline
)

/**
 * Contains the timeline of the refund process, including the amount refunded, time of refund, and status.
 *
 * @property refundAmount The amount refunded in the smallest currency unit (e.g., IRR).
 * @property refundTime The time when the refund was processed.
 * @property refundStatus The status of the refund (e.g., "completed", "pending").
 */
@Serializable
data class PaymentRefundTimeline(
    @SerialName("refund_amount") val refundAmount: Int,
    @SerialName("refund_time") val refundTime: String,
    @SerialName("refund_status") val refundStatus: String
)