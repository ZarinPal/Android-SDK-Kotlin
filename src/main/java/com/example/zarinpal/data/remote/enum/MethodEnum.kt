package com.example.zarinpal.data.remote.enum

import kotlinx.serialization.Serializable

/**
 * Defines supported payment methods.
 */
@Serializable
enum class MethodEnum {
    PAYA, // Interbank transfer using the PAYA system
    CARD  // Payment via bank card
}
