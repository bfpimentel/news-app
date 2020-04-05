package dev.pimentel.search

import dev.pimentel.search.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val viewModelModule = module {
    viewModel { SearchViewModel() }
}

val searchModules = listOf(
    viewModelModule
)
