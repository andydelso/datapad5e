package com.ddaypunk.datapad5e.ui.extension

import datapad5e.composeapp.generated.resources.Res
import datapad5e.composeapp.generated.resources.absorb_energy
import datapad5e.composeapp.generated.resources.acid_dart
import datapad5e.composeapp.generated.resources.acid_splash
import datapad5e.composeapp.generated.resources.acid_wind
import datapad5e.composeapp.generated.resources.acidic_strike
import datapad5e.composeapp.generated.resources.affect_mind
import datapad5e.composeapp.generated.resources.affliction
import datapad5e.composeapp.generated.resources.agile_defense
import datapad5e.composeapp.generated.resources.alter_self
import datapad5e.composeapp.generated.resources.dark_aura
import datapad5e.composeapp.generated.resources.dark_side_tendrils
import datapad5e.composeapp.generated.resources.force_barrier
import datapad5e.composeapp.generated.resources.force_jump
import datapad5e.composeapp.generated.resources.force_lightning
import datapad5e.composeapp.generated.resources.force_storm
import datapad5e.composeapp.generated.resources.ionic_strike
import datapad5e.composeapp.generated.resources.shield
import org.jetbrains.compose.resources.DrawableResource

fun String.toPowerImage(): DrawableResource? {
    return when(this.lowercase()) {
        "absorb energy" -> Res.drawable.absorb_energy
        "acid dart" -> Res.drawable.acid_dart
        "acid splash" -> Res.drawable.acid_splash
        "acid wind" -> Res.drawable.acid_wind
        "acidic strike" -> Res.drawable.acidic_strike
        "agile defense"-> Res.drawable.agile_defense
        "affect mind" -> Res.drawable.affect_mind
        "affliction" -> Res.drawable.affliction
        "alter self" -> Res.drawable.alter_self
        "dark aura" -> Res.drawable.dark_aura
        "dark side tendrils" -> Res.drawable.dark_side_tendrils
        "energy shield", "greater energy shield" -> Res.drawable.shield
        "force barrier" -> Res.drawable.force_barrier
        "force jump", "force leap" -> Res.drawable.force_jump
        "call lightning", "force lightning" -> Res.drawable.force_lightning
        "force lightning cone", "force storm" -> Res.drawable.force_storm
        "ionic strike" -> Res.drawable.ionic_strike
        else -> null
    }
}