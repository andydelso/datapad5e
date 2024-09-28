package com.ddaypunk.datapad5e.ui

import com.ddaypunk.datapad5e.data.CastingPeriod
import com.ddaypunk.datapad5e.data.ContentSource
import com.ddaypunk.datapad5e.data.ContentType
import com.ddaypunk.datapad5e.data.ForceAlignment
import com.ddaypunk.datapad5e.data.KtorRemotePowersClient
import com.ddaypunk.datapad5e.data.PowerModel
import com.ddaypunk.datapad5e.data.PowerType
import com.ddaypunk.datapad5e.data.PowersResponseDto
import com.ddaypunk.datapad5e.domain.Resource
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PowersRepository: KoinComponent {
    private val remoteClient: KtorRemotePowersClient by inject()

    suspend fun retrieve(): Resource<Map<Int, List<PowerModel>>> {
        val powers = try {
            val response = remoteClient.getFrom(url = "https://sw5eapi.azurewebsites.net/api/power")
            Resource.Success(response.toModel())
        } catch (e: Exception) {
            Resource.Error(throwable = e)
        }

        return powers
    }
}

private fun List<PowersResponseDto>.toModel(): Map<Int, List<PowerModel>> {
    return this
        .sortedWith(
            compareBy(
                { it.level },
                { it.name }
            )
        )
        .toModelList()
        .groupBy { it.level }
}

private fun List<PowersResponseDto>.toModelList(): List<PowerModel> {
    return this.map {
        PowerModel(
            name = it.name,
            powerType = PowerType.valueOf(it.powerType.uppercase()),
            prerequisite = it.prerequisite,
            level = it.level,
            castingPeriod = CastingPeriod.valueOf(it.castingPeriod.uppercase()),
            castingPeriodText = it.castingPeriodText,
            range = it.range,
            duration = it.duration,
            concentration = it.concentration,
            forceAlignment = ForceAlignment.valueOf(it.forceAlignment.uppercase()),
            description = it.description,
            higherLevelDescription = it.higherLevelDescription,
            contentType = ContentType.valueOf(it.contentType.uppercase()),
            contentSource = ContentSource.valueOf(it.contentSource.uppercase())
        )
    }
}

data class PowersModel(
    val powers: Map<Int, List<PowerModel>>
)