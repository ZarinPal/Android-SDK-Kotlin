package com.example.zarinpal.data.remote.dto.verification

import com.example.zarinpal.data.remote.dto.Config
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the request payload for verifying a payment transaction.
 *
 * @property merchantId Optional identifier for the merchant.
 * @property sandBox Optional flag indicating whether the request should use sandbox (test) mode.
 * @property amount Payment amount in the smallest currency unit (e.g., IRR).
 * @property authority Authority code received from the payment gateway for the transaction.
 */
@Serializable
data class PaymentVerifyRequest(
    @SerialName("merchant_id")
    val merchantId: String? = null,
    val sandBox: Boolean? = null,
    val amount: Int,
    val authority: String
) {
    /**
     * Creates a copy of this request, substituting null values with defaults from [config].
     *
     * @param config Configuration object providing fallback values.
     * @return A new instance of [PaymentVerifyRequest] with completed properties.
     */
    fun copyWithConfig(config: Config): PaymentVerifyRequest = copy(
        merchantId = merchantId ?: config.merchantId,
        sandBox = sandBox ?: config.sandBox
    )
}
