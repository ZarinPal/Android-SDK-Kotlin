package com.example.zarinpal.data.remote.dto.inquiry

import com.example.zarinpal.data.remote.dto.Config
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Request data for inquiring about a payment.
 *
 * @property merchantId Optional merchant identifier.
 * @property sandBox Indicates whether to use sandbox (test) mode.
 * @property authority Required payment authority code.
 */
@Serializable
data class PaymentInquiryRequest(
    @SerialName("merchant_id")
    val merchantId: String? = null,
    val sandBox: Boolean? = null,
    val authority: String
) {

    /**
     * Returns a copy of this request using fallback values from [config]
     * if [merchantId] or [sandBox] are null.
     */
    fun copyWithConfig(config: Config): PaymentInquiryRequest = copy(
        merchantId = merchantId ?: config.merchantId,
        sandBox = sandBox ?: config.sandBox
    )
}
