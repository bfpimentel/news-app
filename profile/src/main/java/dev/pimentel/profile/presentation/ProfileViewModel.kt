package dev.pimentel.profile.presentation

import dev.pimentel.core.abstractions.BaseViewModel
import dev.pimentel.core.schedulerprovider.SchedulerProvider
import dev.pimentel.core.usecases.GetErrorMessage

class ProfileViewModel(
    schedulerProvider: SchedulerProvider,
    getErrorMessage: GetErrorMessage
) : BaseViewModel(
    schedulerProvider,
    getErrorMessage
), ProfileContract.ViewModel
