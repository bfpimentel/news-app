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
    private val schedulerProvider: SchedulerProvider,
    private val getErrorMessage: GetErrorMessage
) : ViewModel(), BaseContract.ViewModel {

    private val compositeDisposable = CompositeDisposable()

    private val error = MutableLiveData<String>()
    private val isLoading = MutableLiveData<Unit>()
    private val isNotLoading = MutableLiveData<Unit>()

    override fun error(): LiveData<String> = error

    override fun isLoading(): LiveData<Unit> = isLoading

    override fun isNotLoading(): LiveData<Unit> = isNotLoading

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    protected fun postErrorMessage(throwable: Throwable) {
        error.postValue(getErrorMessage(GetErrorMessageParams(throwable)))
    }

    protected fun <T> Single<T>.subscribe(
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit
    ) = compositeDisposable.add(this.subscribe(onSuccess, onError))

    protected fun Completable.subscribe(
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) = compositeDisposable.add(this.subscribe(onSuccess, onError))

    protected fun <T> observeOnUIAfterSingleResult(upstream: Single<T>) =
        SingleTransformer<T, T> {
            upstream
                .subscribeOn(schedulerProvider.ui)
                .observeOn(schedulerProvider.io)
        }

    protected fun observeOnUIAfterCompletableResult(upstream: Completable) =
        CompletableTransformer {
            upstream
                .subscribeOn(schedulerProvider.ui)
                .observeOn(schedulerProvider.io)
        }
}
