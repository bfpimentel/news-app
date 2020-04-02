package dev.pimentel.search.presentation

import dev.pimentel.core.abstractions.BaseViewModel
import dev.pimentel.core.schedulerprovider.SchedulerProvider
import dev.pimentel.core.usecases.GetErrorMessage

class SearchViewModel(
    schedulerProvider: SchedulerProvider,
    getErrorMessage: GetErrorMessage
) : BaseViewModel(
    schedulerProvider,
    getErrorMessage
), SearchContract.ViewModel
