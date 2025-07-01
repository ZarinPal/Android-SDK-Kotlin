package com.example.zarinpal.data.remote.dto.fee


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Keep
@Serializable
data class FeeData(
    @SerialName("amount")
    val amount: Long,
    @SerialName("fee")
    val fee: Int,
    @SerialName("fee_type")
    val feeType: String,
    @SerialName("suggested_amount")
    val suggestedAmount: Long,
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String
)



@Keep
@Serializable
data class PaymentFeeResponse(
    @SerialName("data")
    val data: FeeData,
    @SerialName("errors")
    val errors: List<JsonElement>
)