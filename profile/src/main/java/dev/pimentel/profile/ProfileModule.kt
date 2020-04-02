package dev.pimentel.profile

import dev.pimentel.profile.presentation.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val viewModelsModule = module {
    viewModel { ProfileViewModel(get(), get()) }
}

val profileModules = listOf(
    viewModelsModule
)
