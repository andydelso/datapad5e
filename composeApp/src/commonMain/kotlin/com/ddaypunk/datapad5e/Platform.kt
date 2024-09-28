package com.ddaypunk.datapad5e

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform