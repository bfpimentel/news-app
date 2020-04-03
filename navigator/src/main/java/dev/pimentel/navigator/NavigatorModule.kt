package dev.pimentel.navigator

import org.koin.dsl.module

private val module = module {
    val navigator: Navigator = NavigatorImpl()

    single<NavigatorBinder> { navigator }
    single<FeedNavigator> { navigator }
}

val navigatorModules = listOf(module)
