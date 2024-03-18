package com.ddaypunk.datapad5e

import com.ddaypunk.datapad5e.getPlatform

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}