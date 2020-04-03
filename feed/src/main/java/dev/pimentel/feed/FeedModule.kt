package dev.pimentel.feed

import dev.pimentel.feed.presentation.FeedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val viewModelModule = module {
    viewModel { FeedViewModel(get(), get(), get()) }
}

val feedModules = listOf(
    viewModelModule
)
