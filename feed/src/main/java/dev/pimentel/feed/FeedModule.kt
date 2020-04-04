package dev.pimentel.feed

import dev.pimentel.feed.presentation.FeedViewModel
import dev.pimentel.navigator.Navigator
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val viewModelModule = module {
    viewModel { FeedViewModel(get(), get(), get<Navigator>()) }
}

val feedModules = listOf(
    viewModelModule
)
