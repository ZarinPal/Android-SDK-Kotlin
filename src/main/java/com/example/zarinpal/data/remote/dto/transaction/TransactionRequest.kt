package com.example.zarinpal.data.remote.dto.transaction

import androidx.annotation.Keep
import com.example.zarinpal.data.remote.enum.FilterEnum
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data class for transaction query request.
 *
 * @property terminalId Identifier of the terminal making the request.
 * @property filter Criteria used to filter transactions (e.g., completed, pending).
 * @property limit Maximum number of transactions to fetch.
 * @property offset Starting index for pagination.
 * @property token Optional authentication token.
 */
@Keep
@Serializable
data class TransactionRequest(
    @SerialName("terminal_id")
    val terminalId: String,
    val filter: FilterEnum,
    val limit: Int,
    val offset: Int,
    val token: String? = null
)
