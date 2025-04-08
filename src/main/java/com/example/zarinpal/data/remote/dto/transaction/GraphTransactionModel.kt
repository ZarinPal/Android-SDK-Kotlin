package com.example.zarinpal.data.remote.dto.transaction

import kotlinx.serialization.Serializable

/**
 * Represents a GraphQL request used to fetch transaction data.
 *
 * @property query The GraphQL query string to be executed.
 * @property variables The set of parameters required for retrieving transactions,
 * such as terminalId, filter criteria, pagination limits, and authentication token.
 */
@Serializable
data class GraphTransactionModel(
    val query: String,
    val variables: TransactionRequest
)
