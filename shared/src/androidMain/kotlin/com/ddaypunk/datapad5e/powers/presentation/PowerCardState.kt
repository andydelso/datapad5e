package com.ddaypunk.datapad5e.powers.presentation

data class PowerCardState(
    val title: String,
    val subtitle: String,
    val level: Int,
    val onClick: () -> Unit
)