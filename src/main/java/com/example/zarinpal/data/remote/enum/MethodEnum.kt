package com.example.zarinpal.data.remote.enum

import kotlinx.serialization.Serializable

@Serializable
enum class MethodEnum {
    PAYA,
    CARD;

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
