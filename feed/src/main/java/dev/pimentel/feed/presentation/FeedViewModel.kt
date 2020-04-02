package dev.pimentel.feed.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.pimentel.core.abstractions.BaseViewModel
import dev.pimentel.core.schedulerprovider.SchedulerProvider
import dev.pimentel.core.usecases.GetErrorMessage
import dev.pimentel.navigator.Navigator

class FeedViewModel(
    schedulerProvider: SchedulerProvider,
    getErrorMessage: GetErrorMessage,
    private val navigator: Navigator
) : BaseViewModel(
    schedulerProvider,
    getErrorMessage
), FeedContract.ViewModel {

    private val testText = MutableLiveData<String>()

    override fun initialize() {
        testText.postValue("Teste Fragmento Feed")
    }

    override fun testNavigator() {
    }

    override fun testText(): LiveData<String> = testText
}
