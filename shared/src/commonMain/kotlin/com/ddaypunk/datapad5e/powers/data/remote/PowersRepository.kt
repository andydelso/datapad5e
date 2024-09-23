package com.ddaypunk.datapad5e.powers.data.remote

import com.ddaypunk.datapad5e.core.domain.util.Resource

class PowersRepository(
    private val remoteClient: KtorRemotePowersClient
) {
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



