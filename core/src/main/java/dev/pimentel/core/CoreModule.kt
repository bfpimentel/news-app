package dev.pimentel.core

import dev.pimentel.core.schedulerprovider.SchedulerProvider
import dev.pimentel.core.schedulerprovider.SchedulerProviderImpl
import dev.pimentel.core.usecases.GetErrorMessage
import org.koin.dsl.module

private val useCasesModule = module {
    single { GetErrorMessage() }
}

private val schedulerProviderModule = module {
    single<SchedulerProvider> { SchedulerProviderImpl() }
}

val coreModules = listOf(
    useCasesModule,
    schedulerProviderModule
)
