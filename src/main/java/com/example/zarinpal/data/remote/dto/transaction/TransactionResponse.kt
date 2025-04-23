package com.example.zarinpal.data.remote.dto.transaction

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the full response returned from a transaction fetch request.
 *
 * @property data The actual response payload containing transaction sessions.
 */
@Keep
@Serializable
data class TransactionResponse(
    @SerialName("data")
    val data: TransactionData?
)

/**
 * Container for the session list in the transaction response.
 *
 * @property session A list of [Session] objects representing individual transactions.
 */
@Keep
@Serializable
data class TransactionData(
    @SerialName("Session")
    val session: List<Session>?
)

/**
 * Represents details of a single transaction session.
 *
 * @property id Unique identifier of the transaction.
 * @property status Status of the transaction (e.g., "completed", "pending").
 * @property amount Transaction amount in the smallest currency unit.
 * @property description Brief description of the transaction.
 * @property createdAt Timestamp of when the transaction was initiated.
 */
@Keep
@Serializable
data class Session(
    @SerialName("id")
    val id: String,
    @SerialName("status")
    val status: String?,
    @SerialName("amount")
    val amount: Long,
    @SerialName("description")
    val description: String,
    @SerialName("created_at")
    val createdAt: String
)
