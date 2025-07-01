package com.example.zarinpal.data.remote.dto.unVerified

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/**
 * Represents the response data returned when querying unverified payment transactions.
 *
 * @property data Contains the details of the unverified payment response.
 * @property errors If present, contains any errors returned by the payment gateway.
 */
@Keep
@Serializable
data class PaymentUnVerifiedResponse(
    val data: PaymentUnVerifiedDataResponse,
    val errors: JsonElement?
)

/**
 * Contains the data returned from the unverified payment query.
 *
 * @property code A numeric code indicating the result of the request.
 * @property message A descriptive message explaining the result or error.
 * @property authorities A list of [AuthorityResponse] objects representing the authorities related to the unverified payments.
 */
@Keep
@Serializable
data class PaymentUnVerifiedDataResponse(
    val code: Int,
    val message: String,
    val authorities: List<AuthorityResponse>?
)

/**
 * Represents an authority related to an unverified payment.
 *
 * @property authority The authority code for the payment.
 * @property amount The amount of the unverified payment.
 * @property callbackUrl The URL to which the user will be redirected after payment.
 * @property referer The referer URL from which the request was made.
 * @property date The date of the unverified payment.
 */
@Keep
@Serializable
data class AuthorityResponse(
    val authority: String,
    val amount: Int,
    @SerialName("callback_url") val callbackUrl: String,
    val referer: String,
    val date: String
)
