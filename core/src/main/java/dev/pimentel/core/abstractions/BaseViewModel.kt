package dev.pimentel.core.abstractions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.pimentel.core.schedulerprovider.SchedulerProvider
import dev.pimentel.core.usecases.GetErrorMessage
import dev.pimentel.core.usecases.GetErrorMessageParams
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableTransformer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleTransformer
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel(
    private val schedulerProvider: SchedulerProvider? = null,
    private val getErrorMessage: GetErrorMessage? = null
) : ViewModel(), BaseContract.ViewModel {

    private val compositeDisposable = CompositeDisposable()

    private val error = MutableLiveData<String>()
    protected val isLoading = MutableLiveData<Boolean>()

    override fun error(): LiveData<String> = error

    override fun isLoading(): LiveData<Boolean> = isLoading

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    protected fun postErrorMessage(throwable: Throwable) {
        error.postValue(getErrorMessage!!(GetErrorMessageParams(throwable)))
    }

    protected fun <T> Single<T>.handle(
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit
    ) = compositeDisposable.add(this.subscribe(onSuccess, onError))

    protected fun Completable.handle(
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) = compositeDisposable.add(this.subscribe(onSuccess, onError))

    protected fun <T> observeOnUIAfterSingleResult() =
        SingleTransformer<T, T> {
            it.subscribeOn(schedulerProvider!!.io)
                .observeOn(schedulerProvider.ui)
        }

    protected fun observeOnUIAfterCompletableResult() =
        CompletableTransformer {
            it.subscribeOn(schedulerProvider!!.io)
                .observeOn(schedulerProvider.ui)
        }
}
