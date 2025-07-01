package com.example.zarinpal.data.remote.dto.transaction

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the GraphQL request for fetching transactions.
 *
 * @property query The GraphQL query string to be executed for the transaction operation.
 * @property variables Contains the variables required for the transaction operation,
 * including terminalId, filter, limit, offset, and token.
 */
@Serializable
data class GraphTransactionModel(
    val query :String,
    val variables: TransactionRequest
)