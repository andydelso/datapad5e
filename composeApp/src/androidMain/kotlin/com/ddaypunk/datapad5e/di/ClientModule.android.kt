package com.ddaypunk.datapad5e.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module

actual val httpClientModule = module {
    single<HttpClient> {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json()
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.HEADERS
                filter { request ->
                    request.url.host.contains("sw5eapi")
                }
//                    sanitizeHeader { header -> header == HttpHeaders.Authorization }
            }
        }
    }
}