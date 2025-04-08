package com.example.zarinpal.data.remote.dto.reverse

import com.example.zarinpal.data.remote.dto.Config
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for a payment reversal request.
 *
 * @property merchantId Optional merchant ID. If not provided, a default can be applied.
 * @property sandBox Optional flag for sandbox mode (test environment).
 * @property authority Required authorization code associated with the original payment.
 */
@Serializable
data class PaymentReverseRequest(
    @SerialName("merchant_id")
    val merchantId: String? = null,
    val sandBox: Boolean? = null,
    val authority: String
) {
    /**
     * Creates a copy of the current instance, substituting null values for [merchantId]
     * and [sandBox] with the corresponding values from the provided [config].
     *
     * @param config Configuration containing default values.
     * @return A new [PaymentReverseRequest] instance with updated fields.
     */
    fun copyWithConfig(config: Config): PaymentReverseRequest = copy(
        merchantId = merchantId ?: config.merchantId,
        sandBox = sandBox ?: config.sandBox
    )
}
