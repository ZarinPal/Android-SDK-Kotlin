package com.example.zarinpal.data.remote.dto.transaction

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the response data returned when fetching transactions.
 *
 * @property data Contains the list of transaction sessions returned by the request.
 */
@Keep
@Serializable
data class TransactionResponse(
    @SerialName("data") val data: Data?
)

/**
 * Contains the data structure of the response, including a list of transaction sessions.
 *
 * @property session A list of [Session] objects representing individual transactions.
 */
@Keep
@Serializable
data class Data(
    @SerialName("Session") val session: List<Session>?
)

/**
 * Represents a single transaction session.
 *
 * @property id The unique identifier of the transaction session.
 * @property status The status of the transaction (e.g., "completed", "pending").
 * @property amount The total amount of the transaction.
 * @property description A brief description of the transaction.
 * @property createdAt The date and time when the transaction was created.
 */
@Keep
@Serializable
data class Session(
    @SerialName("id") val id: String,
    @SerialName("status") val status: String?,
    @SerialName("amount") val amount: Long,
    @SerialName("description") val description: String,
    @SerialName("created_at") val createdAt: String
)
