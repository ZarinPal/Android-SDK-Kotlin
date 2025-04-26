package com.example.zarinpal.utils

/**
 * Provides validation functions for input fields used in the ZarinPal payment system.
 */
object Validator {

    fun validateMerchantId(merchantId: String) {
        require(merchantId.matches(Regex("^[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}$"))) {
            "Invalid merchant ID format. Must be a valid UUID."
        }
    }

    fun validateAuthority(authority: String) {
        require(authority.matches(Regex("^[AS][0-9a-zA-Z]{35}$"))) {
            "Invalid authority format. Must start with 'A' or 'S' followed by 35 alphanumeric characters."
        }
    }

    fun validateAmount(amount: Int, minAmount: Int = 1000) {
        require(amount >= minAmount) { "Amount must be at least $minAmount." }
    }

    fun validateCallbackUrl(callbackUrl: String) {
        require(callbackUrl.matches(Regex("^https?://.*$"))) {
            "Invalid callback URL. Must start with http:// or https://."
        }
    }

    fun validateMobile(mobile: String?) {
        if (mobile != null) {
            require(mobile.matches(Regex("^09[0-9]{9}$"))) { "Invalid mobile number format." }
        }
    }

    fun validateEmail(email: String?) {
        if (email != null) {
            require(email.matches(Regex("^[^\s@]+@[^\s@]+\.[^\s@]+$"))) { "Invalid email format." }
        }
    }

    fun validateCurrency(currency: String?) {
        if (currency != null) {
            require(currency == "IRR" || currency == "IRT") {
                "Invalid currency format. Allowed values are 'IRR' or 'IRT'."
            }
        }
    }

    fun validateWages(wages: List<Map<String, Any>>?) {
        wages?.forEach { wage ->
            val iban = wage["iban"] as? String ?: throw IllegalArgumentException("Wage IBAN is required.")
            val amount = wage["amount"] as? Double ?: throw IllegalArgumentException("Wage amount is required.")
            val description = wage["description"] as? String ?: throw IllegalArgumentException("Wage description is required.")

            require(iban.matches(Regex("^[A-Z]{2}[0-9]{2}[0-9A-Z]{1,30}$"))) {
                "Invalid IBAN format in wages."
            }
            require(amount > 0) { "Wage amount must be greater than zero." }
            require(description.length <= 255) { "Wage description must be 255 characters or fewer." }
        }
    }

    fun validateTerminalId(terminalId: String) {
        require(terminalId.isNotEmpty() && terminalId.matches(Regex("^[0-9]+$"))) {
            "Terminal ID must contain only digits and cannot be empty."
        }
    }

    fun validateSessionId(sessionId: String) {
        require(sessionId.isNotEmpty() && sessionId.matches(Regex("^[0-9]+$"))) {
            "Session ID must contain only digits and cannot be empty."
        }
    }

    fun validateLimit(limit: Int) {
        require(limit > 0) { "Limit must be a positive integer." }
    }

    fun validateOffset(offset: Int) {
        require(offset >= 0) { "Offset must be a non-negative integer." }
    }

    fun validateCardPan(cardPan: String?) {
        if (cardPan != null) {
            require(cardPan.matches(Regex("^[0-9]{16}$"))) {
                "Invalid card PAN format. Must be a 16-digit number."
            }
        }
    }
} 
