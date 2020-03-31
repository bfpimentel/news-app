package dev.pimentel.feed.presentation

import dev.pimentel.core.abstractions.BaseViewModel
import dev.pimentel.core.schedulerprovider.SchedulerProvider
import dev.pimentel.core.usecases.GetErrorMessage

class FeedViewModel(
    schedulerProvider: SchedulerProvider,
    getErrorMessage: GetErrorMessage
) : BaseViewModel(
    schedulerProvider,
    getErrorMessage
), FeedContract.ViewModel
