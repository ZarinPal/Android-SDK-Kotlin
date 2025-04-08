package com.example.zarinpal.data.remote.dto.unVerified

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/**
 * Response model for unverified payment queries.
 *
 * @property data Contains the details of the unverified payment.
 * @property errors Optional error information returned by the payment gateway.
 */
@Keep
@Serializable
data class PaymentUnVerifiedResponse(
    val data: PaymentUnVerifiedDataResponse,
    val errors: JsonElement?
)

/**
 * Contains the main response data for unverified payments.
 *
 * @property code Status code representing the result of the query.
 * @property message Descriptive message about the outcome.
 * @property authorities Optional list of authorities related to unverified transactions.
 */
@Keep
@Serializable
data class PaymentUnVerifiedDataResponse(
    val code: Int,
    val message: String,
    val authorities: List<AuthorityResponse>?
)

/**
 * Represents an authority entry related to an unverified transaction.
 *
 * @property authority Unique identifier for the transaction.
 * @property amount Transaction amount in the smallest currency unit.
 * @property callbackUrl URL to redirect the user after payment.
 * @property referer Referring URL from which the request originated.
 * @property date Timestamp of the transaction event.
 */
@Keep
@Serializable
data class AuthorityResponse(
    val authority: String,
    val amount: Int,
    @SerialName("callback_url")
    val callbackUrl: String,
    val referer: String,
    val date: String
)
