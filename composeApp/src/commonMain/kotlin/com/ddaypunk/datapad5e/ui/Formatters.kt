package com.ddaypunk.datapad5e.ui

fun Int.getFormattedLevel() = when(this.toString()) {
    "1" -> "1st-level"
    "2" -> "2nd-level"
    "3" -> "3rd-level"
    in "456789" -> "${this}th-level"
    else -> "0-level"
}