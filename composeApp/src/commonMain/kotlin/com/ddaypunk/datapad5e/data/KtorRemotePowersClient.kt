package com.ddaypunk.datapad5e.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KtorRemotePowersClient : KoinComponent {
    private val httpClient: HttpClient by inject()

    suspend fun getFrom(url: String): List<PowersResponseDto> {
        val response = httpClient.request(url)

        when(response.status.value) {
            in 200.. 299 -> Unit
            else -> throw Exception("Error from $url")
        }

        return try {
            response.body<List<PowersResponseDto>>()
        } catch (e: Exception) {
            throw Exception("Error parsing response from $url")
        }
    }
}