package com.ddaypunk.datapad5e.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module

actual val httpClientModule = module {
    single<HttpClient> {
        HttpClient(Darwin) {
            install(ContentNegotiation) {
                json()
            }
        }
    }
}