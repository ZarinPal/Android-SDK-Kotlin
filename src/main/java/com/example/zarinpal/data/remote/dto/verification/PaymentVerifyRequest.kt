
package com.example.zarinpal.data.remote.dto.verification

import com.example.zarinpal.data.remote.dto.Config
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the request data required for verifying a payment.
 *
 * @property merchantId The unique identifier for the merchant (nullable).
 * @property sandBox Indicates if the payment should be processed in sandbox mode (test mode).
 * @property amount The total amount of the payment in the smallest currency unit (e.g., IRR).
 * @property authority The authority code provided by the payment gateway for the transaction.
 */
@Serializable
data class PaymentVerifyRequest(
    @SerialName("merchant_id")
    val merchantId: String?=null,
    val sandBox :Boolean?=null,
    val amount: Int,
    val authority :String,
){
    /**
     * Creates a copy of the request with the merchantId and sandBox values
     * replaced by the ones from the provided [Config] if they are null.
     *
     * @param config The [Config] object that provides default values for merchantId and sandBox.
     * @return A new instance of [PaymentVerifyRequest] with updated values.
     */
    fun copyWithConfig(config: Config): PaymentVerifyRequest {
        return this.copy(
            merchantId = this.merchantId ?: config.merchantId,
            sandBox = this.sandBox ?: config.sandBox
        )
    }
}
