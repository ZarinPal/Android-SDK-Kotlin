package com.example.zarinpal.data.remote.dto.refund

import kotlinx.serialization.Serializable

/**
 * Represents a GraphQL request for initiating a payment refund.
 *
 * @property query GraphQL query string to execute the refund.
 * @property variables Parameters required to process the refund,
 * including session ID, description, method, reason, amount, and token.
 */
@Serializable
data class GraphRefundModel(
    val query: String,
    val variables: PaymentRefundRequest
)
