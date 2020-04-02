package dev.pimentel.feed.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.pimentel.core.abstractions.BaseViewModel
import dev.pimentel.core.schedulerprovider.SchedulerProvider
import dev.pimentel.core.usecases.GetErrorMessage

class FeedViewModel(
        schedulerProvider: SchedulerProvider,
        getErrorMessage: GetErrorMessage
) : BaseViewModel(
        schedulerProvider,
        getErrorMessage
), FeedContract.ViewModel {

    private val testText = MutableLiveData<String>()

    override fun initialize() {
        testText.postValue("Teste Fragmento Feed")
    }

    override fun testText(): LiveData<String> = testText
}
