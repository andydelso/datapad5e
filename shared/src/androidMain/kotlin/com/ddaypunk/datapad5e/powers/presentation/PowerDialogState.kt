package com.ddaypunk.datapad5e.powers.presentation

data class PowerDialogState(
    val title: String,
    val subtitle: String,
    val alignment: String,
    val castingTime: String,
    val range: String,
    val duration: String,
    val concentration: String,
    val prerequisite: String? = null,
    val description: String,
    val source: String,
    val onDismiss: () -> Unit
)
