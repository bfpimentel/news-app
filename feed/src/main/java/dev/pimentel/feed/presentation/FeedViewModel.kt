package dev.pimentel.feed.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.pimentel.core.abstractions.BaseViewModel
import dev.pimentel.core.schedulerprovider.SchedulerProvider
import dev.pimentel.core.usecases.GetErrorMessage
import dev.pimentel.navigator.FeedNavigator

internal class FeedViewModel(
    private val navigator: FeedNavigator,
    getErrorMessage: GetErrorMessage,
    schedulerProvider: SchedulerProvider
) : BaseViewModel(
    schedulerProvider,
    getErrorMessage
), FeedContract.ViewModel {

    private val testText = MutableLiveData<String>()

    override fun initialize() {
        testText.postValue("Teste Fragmento Feed")
    }

    override fun testNavigator() {
        navigator.routeToProfile()
    }

    override fun testText(): LiveData<String> = testText
}
