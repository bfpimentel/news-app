package dev.pimentel.feed.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.pimentel.core.abstractions.BaseViewModel
import dev.pimentel.core.abstractions.NoParams
import dev.pimentel.core.schedulerprovider.SchedulerProvider
import dev.pimentel.core.usecases.GetErrorMessage
import dev.pimentel.feed.data.usecases.FetchHeadlines
import dev.pimentel.navigator.FeedNavigator

internal class FeedViewModel(
    private val navigator: FeedNavigator,
    private val fetchHeadlines: FetchHeadlines,
    getErrorMessage: GetErrorMessage,
    schedulerProvider: SchedulerProvider
) : BaseViewModel(
    schedulerProvider,
    getErrorMessage
), FeedContract.ViewModel {

    private val testText = MutableLiveData<String>()

    override fun initialize() {
        fetchHeadlines(NoParams)
            .compose(observeOnUIAfterSingleResult())
            .handle(
                {
                    Log.d("TESTE SUCESSO", it.toString())
                    testText.postValue("Teste Fragmento Feed")
                },
                ::postErrorMessage
            )
    }

    override fun testNavigator() {
        navigator.routeToProfile()
    }

    override fun testText(): LiveData<String> = testText
}
