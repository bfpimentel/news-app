package dev.pimentel.core.abstractions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.pimentel.core.schedulerprovider.SchedulerProvider
import dev.pimentel.core.shared.ViewModelTest
import dev.pimentel.core.usecases.GetErrorMessage
import dev.pimentel.core.usecases.GetErrorMessageParams
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.junit.Assert.*
import org.junit.Test

internal class TestCompletable : UseCase<NoParams, Completable> {

    override fun invoke(params: NoParams): Completable = Completable.complete()
}

internal class TestSingle : UseCase<NoParams, Single<String>> {

    override fun invoke(params: NoParams): Single<String> = Single.just("")
}

internal interface TestContract {

    interface ViewModel : BaseContract.ViewModel {
        fun testInputForCompletable(input: String)
        fun testInputForSingle()

        fun testOutput(): LiveData<String>
    }
}

internal class TestViewModel(
    private val testCompletable: TestCompletable,
    private val testSingle: TestSingle,
    getErrorMessage: GetErrorMessage,
    schedulerProvider: SchedulerProvider
) : BaseViewModel(
    schedulerProvider,
    getErrorMessage
), TestContract.ViewModel {

    private val testOutput = MutableLiveData<String>()

    override fun testInputForCompletable(input: String) {
        testCompletable(NoParams)
            .compose(observeOnUIAfterCompletableResult())
            .doOnSubscribe { isLoading.postValue(true) }
            .doFinally { isLoading.postValue(false) }
            .handle({ testOutput.postValue(input) }, ::postErrorMessage)
    }

    override fun testInputForSingle() {
        testSingle(NoParams)
            .compose(observeOnUIAfterSingleResult())
            .doOnSubscribe { isLoading.postValue(true) }
            .doFinally { isLoading.postValue(false) }
            .handle(testOutput::postValue, ::postErrorMessage)
    }

    override fun testOutput(): LiveData<String> = testOutput
}

internal class BaseViewModelTest : ViewModelTest<TestContract.ViewModel>() {

    private val testCompletable = mockk<TestCompletable>()
    private val testSingle = mockk<TestSingle>()

    override lateinit var viewModel: TestContract.ViewModel

    override fun setupSubject() {
        viewModel = TestViewModel(
            testCompletable,
            testSingle,
            getErrorMessage,
            schedulerProvider
        )
    }

    @Test
    fun `should show and hide loading and post error message after failing to test completable`() {
        val error = IllegalArgumentException()
        val params = GetErrorMessageParams(error)
        val message = "message"

        every { testCompletable(NoParams) } returns Completable.error(error)
        every { getErrorMessage(params) } returns message

        viewModel.testInputForCompletable("")

        assertTrue(viewModel.isLoading().value!!)

        testScheduler.triggerActions()

        assertNull(viewModel.testOutput().value)
        assertEquals(viewModel.error().value, message)

        assertFalse(viewModel.isLoading().value!!)

        verify {
            testCompletable(NoParams)
            getErrorMessage(params)
        }
        confirmVerified(testCompletable, testSingle, getErrorMessage)
    }

    @Test
    fun `should show and hide loading and post test output after testing completable successfully`() {
        val message = "message"

        every { testCompletable(NoParams) } returns Completable.complete()

        viewModel.testInputForCompletable(message)

        assertTrue(viewModel.isLoading().value!!)

        testScheduler.triggerActions()

        assertNull(viewModel.error().value)
        assertEquals(viewModel.testOutput().value, message)

        assertFalse(viewModel.isLoading().value!!)

        verify {
            testCompletable(NoParams)
        }
        confirmVerified(testCompletable, testSingle, getErrorMessage)
    }

    @Test
    fun `should show and hide loading and post error message after failing to test single`() {
        val error = IllegalArgumentException()
        val params = GetErrorMessageParams(error)
        val message = "message"

        every { testSingle(NoParams) } returns Single.error(error)
        every { getErrorMessage(params) } returns message

        viewModel.testInputForSingle()

        assertTrue(viewModel.isLoading().value!!)

        testScheduler.triggerActions()

        assertNull(viewModel.testOutput().value)
        assertEquals(viewModel.error().value, message)

        assertFalse(viewModel.isLoading().value!!)

        verify {
            testSingle(NoParams)
            getErrorMessage(params)
        }
        confirmVerified(testCompletable, testSingle, getErrorMessage)
    }

    @Test
    fun `should show and hide loading and post test output after testing single successfully`() {
        val message = "message"

        every { testSingle(NoParams) } returns Single.just(message)

        viewModel.testInputForSingle()

        assertTrue(viewModel.isLoading().value!!)

        testScheduler.triggerActions()

        assertNull(viewModel.error().value)
        assertEquals(viewModel.testOutput().value, message)

        assertFalse(viewModel.isLoading().value!!)

        verify {
            testSingle(NoParams)
        }
        confirmVerified(testCompletable, testSingle, getErrorMessage)
    }
}
