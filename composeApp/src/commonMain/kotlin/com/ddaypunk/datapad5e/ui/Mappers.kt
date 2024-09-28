package com.ddaypunk.datapad5e.ui

import datapad5e.composeapp.generated.resources.Res
import datapad5e.composeapp.generated.resources.absorb_energy
import datapad5e.composeapp.generated.resources.acid_dart
import datapad5e.composeapp.generated.resources.acid_splash
import datapad5e.composeapp.generated.resources.acid_wind
import datapad5e.composeapp.generated.resources.acidic_strike
import datapad5e.composeapp.generated.resources.adrenaline
import datapad5e.composeapp.generated.resources.affect_mind
import datapad5e.composeapp.generated.resources.affliction
import datapad5e.composeapp.generated.resources.alter_self
import datapad5e.composeapp.generated.resources.ionic_strike
import org.jetbrains.compose.resources.DrawableResource

fun String.toPowerImage(): DrawableResource? {
    return when(this.lowercase()) {
        "absorb energy" -> Res.drawable.absorb_energy
        "acid dart" -> Res.drawable.acid_dart
        "acid splash" -> Res.drawable.acid_splash
        "acid wind" -> Res.drawable.acid_wind
        "acidic strike" -> Res.drawable.acidic_strike
        "adrenaline" -> Res.drawable.adrenaline
        "affect mind" -> Res.drawable.affect_mind
        "affliction" -> Res.drawable.affliction
        "alter self" -> Res.drawable.alter_self
        "ionic strike" -> Res.drawable.ionic_strike
        else -> null
    }
}