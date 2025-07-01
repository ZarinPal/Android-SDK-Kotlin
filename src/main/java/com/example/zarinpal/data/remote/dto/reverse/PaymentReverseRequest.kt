
package com.example.zarinpal.data.remote.dto.reverse

import com.example.zarinpal.data.remote.dto.Config
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the request data required to reverse a payment.
 *
 * @property merchantId The unique identifier for the merchant (nullable).
 * @property sandBox Indicates if the payment reversal should be processed in sandbox mode (test mode).
 * @property authority The authorization code for the payment reversal (required).
 */
@Serializable
data class PaymentReverseRequest(
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
     * @return A new instance of [PaymentReverseRequest] with updated values.
     */
    fun copyWithConfig(config: Config): PaymentReverseRequest {
        return this.copy(
            merchantId = this.merchantId ?: config.merchantId,
            sandBox = this.sandBox ?: config.sandBox
        )
    }
}
