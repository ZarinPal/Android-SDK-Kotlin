package com.example.zarinpal.data.remote.enum

import kotlinx.serialization.Serializable

/**
 * Enum representing reasons for payment-related actions such as refunds or cancellations.
 */
@Serializable
enum class ReasonEnum {
    DUPLICATE_TRANSACTION,  // Transaction was submitted more than once
    SUSPICIOUS_TRANSACTION, // Transaction flagged as suspicious
    CUSTOMER_REQUEST,       // Action initiated by customer request
    OTHER                   // Any other unspecified reason
}
