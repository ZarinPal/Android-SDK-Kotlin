package com.example.zarinpal.data.remote.dto.refund

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Top-level response returned after initiating a payment refund.
 *
 * @property data Details of the refund operation.
 */
@Serializable
data class PaymentRefundResponseModel(
    val data: PaymentRefundDataModel
)

/**
 * Container for refund resource information.
 *
 * @property resource Detailed refund transaction data.
 */
@Serializable
data class PaymentRefundDataModel(
    val resource: PaymentRefundResponse
)

/**
 * Refund transaction details.
 *
 * @property terminalId Terminal ID used for the refund.
 * @property id Unique identifier of the refund transaction.
 * @property amount Refunded amount in the smallest currency unit (e.g., IRR).
 * @property timeline Timeline of the refund process.
 */
@Serializable
data class PaymentRefundResponse(
    @SerialName("terminal_id") val terminalId: String,
    val id: String,
    val amount: Int,
    val timeline: PaymentRefundTimeline
)

/**
 * Timeline of the refund process.
 *
 * @property refundAmount Refunded amount in the smallest currency unit (e.g., IRR).
 * @property refundTime Timestamp of when the refund was processed.
 * @property refundStatus Current status of the refund (e.g., "completed", "pending").
 */
@Serializable
data class PaymentRefundTimeline(
    @SerialName("refund_amount") val refundAmount: Int,
    @SerialName("refund_time") val refundTime: String,
    @SerialName("refund_status") val refundStatus: String
)
