package dev.pimentel.navigator

import org.koin.dsl.module

private val module = module {
    single<Navigator> { NavigatorImpl() }
}

val navigatorModules = listOf(module)
