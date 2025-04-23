package com.example.zarinpal.data.remote.dto.unVerified

import com.example.zarinpal.data.remote.dto.Config
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Request payload for unverified payment operations.
 *
 * @property merchantId Optional merchant ID; identifies the merchant making the request.
 * @property sandBox Optional flag to indicate if the request is in sandbox (test) mode.
 */
@Serializable
data class PaymentUnVerifiedRequest(
    @SerialName("merchant_id")
    val merchantId: String? = null,
    val sandBox: Boolean? = null
) {
    /**
     * Returns a new instance with missing fields populated from the provided [config].
     *
     * @param config Configuration supplying default values.
     * @return Updated [PaymentUnVerifiedRequest] instance.
     */
    fun copyWithConfig(config: Config): PaymentUnVerifiedRequest = copy(
        merchantId = merchantId ?: config.merchantId,
        sandBox = sandBox ?: config.sandBox
    )
}
