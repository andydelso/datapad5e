package com.ddaypunk.datapad5e.ui

import org.jetbrains.compose.resources.DrawableResource

data class PowerCardState(
    val title: String,
    val subtitle: String,
    val level: Int,
    val image: DrawableResource? = null,
    val onClick: () -> Unit
)