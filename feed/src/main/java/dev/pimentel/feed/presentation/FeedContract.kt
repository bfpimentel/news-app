package dev.pimentel.feed.presentation

import androidx.lifecycle.LiveData
import dev.pimentel.core.abstractions.BaseContract

interface FeedContract {

    interface ViewModel : BaseContract.ViewModel {
        fun initialize()
        fun testNavigator()

        fun testText(): LiveData<String>
    }
}
