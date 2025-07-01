package com.example.zarinpal.data.remote.dto.transaction

import androidx.annotation.Keep
import com.example.zarinpal.data.remote.enum.FilterEnum
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the request data required to fetch transactions.
 *
 * @property terminalId The unique identifier for the terminal from which transactions are being requested.
 * @property filter A filter string to apply to the transactions (e.g., "completed", "pending").
 * @property limit The maximum number of transactions to return.
 * @property offset The starting point for fetching transactions (used for pagination).
 * @property token A token used for additional security (nullable).
 */
@Keep
@Serializable
data class TransactionRequest(
    @SerialName("terminal_id")
    val terminalId: String,
    val filter: FilterEnum,
    val limit: Int,
    val offset: Int,
    val token: String? = null,
)