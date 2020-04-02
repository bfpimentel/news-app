package dev.pimentel.navigator

import org.koin.dsl.module

private val module = module {
    factory<Navigator> { NavigatorImpl() }
}

val navigatorModules = listOf(module)
