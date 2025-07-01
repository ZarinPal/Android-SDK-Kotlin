package com.example.zarinpal.data.remote.dto.fee
import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class PaymentFeeRequest(
    @SerialName("merchant_id")
    val merchantId: String,
    val amount: Long,
    val currency: String = "IRR",
)