package com.ddaypunk.datapad5e.di

import com.ddaypunk.datapad5e.ui.PowersRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { PowersRepository() }
}