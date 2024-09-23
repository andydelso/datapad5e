package com.ddaypunk.datapad5e.powers.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request

class KtorRemotePowersClient(
    private val httpClient: HttpClient
) {
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