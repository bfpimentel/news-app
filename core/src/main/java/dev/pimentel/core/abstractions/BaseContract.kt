package dev.pimentel.core.abstractions

import androidx.lifecycle.LiveData

interface BaseContract {

    interface ViewModel {
        fun isLoading(): LiveData<Boolean>
        fun error(): LiveData<String>
    }
}
