package com.example.zarinpal.data.remote.dto.create

import androidx.annotation.Keep
import com.example.zarinpal.data.remote.dto.Config
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the request data required to create a payment.
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
     * Returns a copy of this request with merchantId and sandBox populated from [config] if they are null.
     */
    fun copyWithConfig(config: Config): CreatePaymentRequest {
        return copy(
            merchantId = merchantId ?: config.merchantId,
            sandBox = sandBox ?: config.sandBox
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
    val description: String
)
