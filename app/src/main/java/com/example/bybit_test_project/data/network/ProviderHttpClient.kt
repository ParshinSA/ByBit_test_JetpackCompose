package com.example.bybit_test_project.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object ProviderHttpClient {

    private const val TIME_OUT = 10_000

    private var httpClient: HttpClient? = null

    fun getHttpClient(): HttpClient {
        if (httpClient == null)
            httpClient = HttpClient(Android) {

                install(ContentNegotiation) {
                    json(Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                    })
                }

                install(Logging)

                engine {
                    connectTimeout = TIME_OUT
                    socketTimeout = TIME_OUT
                }
            }

        return httpClient!!
    }

}