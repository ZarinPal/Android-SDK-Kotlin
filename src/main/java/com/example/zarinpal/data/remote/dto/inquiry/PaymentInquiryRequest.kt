
package com.example.zarinpal.data.remote.dto.inquiry

import com.example.zarinpal.data.remote.dto.Config
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the request data required to inquire about a payment.
 *
 * @property merchantId The unique identifier for the merchant (nullable).
 * @property sandBox Indicates if the payment inquiry should be processed in sandbox mode (test mode).
 * @property authority The authorization code for the payment inquiry (required).
 */
@Serializable
data class PaymentInquiryRequest(
    @SerialName("merchant_id")
    val merchantId: String?=null,
    val sandBox :Boolean?=null,
    val authority :String,
){
    /**
     * Creates a copy of the request with the merchantId and sandBox values
     * replaced by the ones from the provided [Config] if they are null.
     *
     * @param config The [Config] object that provides default values for merchantId and sandBox.
     * @return A new instance of [PaymentInquiryRequest] with updated values.
     */
    fun copyWithConfig(config: Config): PaymentInquiryRequest {
        return this.copy(
            merchantId = this.merchantId ?: config.merchantId,
            sandBox = this.sandBox ?: config.sandBox
        )
    }
}
