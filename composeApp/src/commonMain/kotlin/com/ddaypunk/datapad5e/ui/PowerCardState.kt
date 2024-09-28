package com.ddaypunk.datapad5e.ui

data class PowerCardState(
    val title: String,
    val subtitle: String,
    val level: Int,
    val image: Int? = null,
    val onClick: () -> Unit
)