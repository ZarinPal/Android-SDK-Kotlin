package com.example.zarinpal.data.remote.dto.refund

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the GraphQL request for initiating a payment refund.
 *
 * @property query The GraphQL query string to be executed for the refund operation.
 * @property variables Contains the variables required for the refund operation,
 * including session ID, description, method, reason, amount, and token.
 */
@Serializable
data class GraphRefundModel(
    val query :String,
    val variables: PaymentRefundRequest
)