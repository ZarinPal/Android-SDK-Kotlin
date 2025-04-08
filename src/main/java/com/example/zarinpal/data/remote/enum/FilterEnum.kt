package com.example.zarinpal.data.remote.enum

import kotlinx.serialization.Serializable

/**
 * Specifies the available filter options for querying payment records.
 */
@Serializable
enum class FilterEnum {
    ALL,        // Includes all payment records
    PAID,       // Payments that have been successfully made
    VERIFIED,   // Payments that have been confirmed as verified
    TRASH,      // Payments that are marked as discarded
    ACTIVE,     // Ongoing or currently valid payment transactions
    REFUNDED    // Payments that have been successfully refunded
}
