package com.example.zarinpal.data.remote.enum

import kotlinx.serialization.Serializable

@Serializable
enum class FilterEnum {
    ALL,
    PAID,
    VERIFIED,
    TRASH,
    ACTIVE,
    REFUNDED;

    fun isFinal(): Boolean = this in listOf(PAID, REFUNDED, TRASH)

    fun isActive(): Boolean = this in listOf(ACTIVE, VERIFIED)

    fun needsVerification(): Boolean = this == ACTIVE || this == ALL

    fun canBeRefunded(): Boolean = this in listOf(PAID, VERIFIED)

    override fun toString(): String = name.lowercase().replaceFirstChar { it.uppercase() }
}

object EnvironmentConfig {
    enum class Environment(val baseUrl: String) {
        SANDBOX("https://sandbox.zarinpal.com/pg/rest/WebGate/"),
        PRODUCTION("https://www.zarinpal.com/pg/rest/WebGate/")
    }

    private var environment: Environment = Environment.SANDBOX

    val baseUrl: String
        get() = environment.baseUrl

    fun useSandbox() {
        environment = Environment.SANDBOX
    }

    fun useProduction() {
        environment = Environment.PRODUCTION
    }

    fun isSandbox(): Boolean = environment == Environment.SANDBOX

    fun isProduction(): Boolean = environment == Environment.PRODUCTION

    fun currentEnvironment(): Environment = environment

    fun debugInfo(): String = "Environment: ${environment.name}, Base URL: ${baseUrl}"

    fun resolveEndpoint(path: String): String = baseUrl + path.trimStart('/')
} 
 
