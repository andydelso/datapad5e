package com.ddaypunk.datapad5e.data

import kotlinx.serialization.Serializable

@Serializable
data class PowersResponseDto(
    val name: String = "",
    val powerTypeEnum: Int = 0, // we should map this to an Enum properly
    val powerType: String = "Tech",
    val prerequisite: String? = null,
    val level: Int = 1,
    val castingPeriodEnum: Int = 0, // we should map this to an Enum properly
    val castingPeriod: String = "None",
    val castingPeriodText: String = "",
    val range: String = "", // should this be an Enum internally?
    val duration: String = "", // should this be an Enum internally?
    val concentration: Boolean = false,
    val forceAlignmentEnum: Int = 0, // we should map this to an Enum properly
    val forceAlignment: String = "None",
    val description: String = "",
    val higherLevelDescription: String? = null,
    val contentTypeEnum: Int, // we should map this to an Enum properly
    val contentType: String = "",
    val contentSourceEnum: Int = 0, // we should map this to an Enum properly
    val contentSource: String = "",
    val partitionKey: String = "",
    val rowKey: String = "",
    val timestamp: String = "",
    val eTag: String = ""
)
