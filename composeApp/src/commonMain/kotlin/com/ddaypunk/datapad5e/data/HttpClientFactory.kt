package com.ddaypunk.datapad5e.data

import io.ktor.client.HttpClient

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class HttpClientFactory() {
    fun create(): HttpClient
}