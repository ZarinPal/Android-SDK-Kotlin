
package com.example.zarinpal.data.remote.dto.unVerified

import com.example.zarinpal.data.remote.dto.Config
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the request data required for unverified payment transactions.
 *
 * @property merchantId The unique identifier for the merchant (nullable).
 * @property sandBox Indicates if the request should be processed in sandbox mode (test mode).
 */
@Serializable
data class PaymentUnVerifiedRequest(
    @SerialName("merchant_id")
    val merchantId: String?=null,
    val sandBox :Boolean?=null,
){
    /**
     * Creates a copy of the request with the merchantId and sandBox values
     * replaced by the ones from the provided [Config] if they are null.
     *
     * @param config The [Config] object that provides default values for merchantId and sandBox.
     * @return A new instance of [PaymentUnVerifiedRequest] with updated values.
     */
    fun copyWithConfig(config: Config): PaymentUnVerifiedRequest {
        return this.copy(
            merchantId = this.merchantId ?: config.merchantId,
            sandBox = this.sandBox ?: config.sandBox
        )
    }
}
