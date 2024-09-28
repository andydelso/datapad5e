package com.ddaypunk.datapad5e.di

import com.ddaypunk.datapad5e.data.KtorRemotePowersClient
import org.koin.core.module.Module
import org.koin.dsl.module

expect val httpClientModule: Module

val ktorClientModule = module {
    factory { KtorRemotePowersClient() }
}