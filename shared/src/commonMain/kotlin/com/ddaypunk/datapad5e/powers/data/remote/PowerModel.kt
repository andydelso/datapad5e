package com.ddaypunk.datapad5e.powers.data.remote

data class PowerModel(
    val name: String,
    val powerType: PowerType,
    val prerequisite: String? = null,
    val level: Int,
    val castingPeriod: CastingPeriod,
    val castingPeriodText: String,
    val range: String,
    val duration: String,
    val concentration: Boolean,
    val forceAlignment: ForceAlignment,
    val description: String,
    val higherLevelDescription: String? = null,
    val contentType: ContentType,
    val contentSource: ContentSource
)

enum class PowerType(
    val displayText: String
) {
    TECH("Tech Power"),
    FORCE("Force Power")
}

enum class CastingPeriod {
    NONE,
    ACTION,
    BONUSACTION,
    REACTION,
    MINUTE,
    HOUR
}

enum class ForceAlignment {
    NONE,
    UNIVERSAL,
    DARK,
    LIGHT
}

enum class ContentType {
    CORE,
}

enum class ContentSource {
    PHB,
    EC
}
