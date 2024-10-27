package com.ddaypunk.datapad5e.ui.model

data class PowerDialogState(
    val title: String,
    val subtitle: String,
    val alignment: String,
    val castingPeriod: String,
    val range: String,
    val duration: String,
    val concentration: String,
    val prerequisite: String? = null,
    val description: String,
    val source: String
)
