package com.ddaypunk.datapad5e.di

import com.ddaypunk.datapad5e.ui.PowersRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        httpClientModule,
        ktorClientModule,
        repositoryModule
    )
}

// for iOS
fun initKoin() = initKoin {}

class KoinHelper : KoinComponent {
    val repository: PowersRepository by inject()
}