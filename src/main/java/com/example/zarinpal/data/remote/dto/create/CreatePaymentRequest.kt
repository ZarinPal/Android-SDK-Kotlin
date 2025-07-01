package com.example.zarinpal.data.remote.dto.create

import androidx.annotation.Keep
import com.example.zarinpal.data.remote.dto.Config
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the request data required to create a payment.
 *
 * @property merchantId The unique identifier for the merchant (nullable).
 * @property sandBox Indicates if the payment should be processed in sandbox mode (test mode).
 * @property description Description of the payment (e.g., "Order #1234").
 * @property callbackUrl The URL to redirect the user to after payment.
 * @property amount The total amount of the payment in the smallest currency unit (e.g., IRR).
 */
@Keep
@Serializable
data class CreatePaymentRequest(
    @SerialName("merchant_id")
    val merchantId: String? = null,
    val sandBox: Boolean? = null,
    val description: String,
    @SerialName("callback_url")
    val callbackUrl: String,
    val amount: Int,
    val metadata: Metadata? = null,
    @SerialName("referrer_id")
    val referrerId: String? = null,
    val currency: String? = null,
    val cardPan: String? = null,
    val wages: List<WagesPaymentRequest>? = null,
) {
    /**
     * Creates a copy of the request with the merchantId and sandBox values
     * replaced by the ones from the provided [Config] if they are null.
     */
    fun copyWithConfig(config: Config): CreatePaymentRequest {
        return this.copy(
            merchantId = this.merchantId ?: config.merchantId,
            sandBox = this.sandBox ?: config.sandBox
        )
    }

    constructor(
        merchantId: String,
        sandBox: Boolean? = null,
        description: String,
        callbackUrl: String,
        amount: Int,
        mobile: String? = null,
        email: String? = null,
        referrerId: String? = null,
        currency: String? = null,
        cardPan: String? = null,
        wages: List<WagesPaymentRequest>? = null
    ) : this(
        merchantId = merchantId,
        sandBox = sandBox,
        description = description,
        callbackUrl = callbackUrl,
        amount = amount,
        metadata = if (!mobile.isNullOrBlank() || !email.isNullOrBlank()) Metadata(mobile, email) else null,
        referrerId = referrerId,
        currency = currency,
        cardPan = cardPan,
        wages = wages
    )

}

@Keep
@Serializable
data class Metadata(
    val mobile: String? = null,
    val email: String? = null
)


@Keep
@Serializable
data class WagesPaymentRequest(
    val iban: String,
    val amount: Int,
    val description: String,
)
