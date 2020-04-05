package dev.pimentel.profile.shared

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.pimentel.core.abstractions.BaseContract
import dev.pimentel.core.schedulerprovider.SchedulerProvider
import dev.pimentel.core.usecases.GetErrorMessage
import io.mockk.mockk
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.Before
import org.junit.Rule

abstract class ViewModelTest<ViewModelType : BaseContract.ViewModel> {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    protected lateinit var testScheduler: TestScheduler
    protected lateinit var schedulerProvider: SchedulerProvider
    protected lateinit var getErrorMessage: GetErrorMessage

    abstract val viewModel: ViewModelType

    abstract fun setupSubject()

    @Before
    fun setupTest() {
        testScheduler = TestScheduler()
        schedulerProvider = TestSchedulerProvider(testScheduler)
        getErrorMessage = mockk()

        setupSubject()
    }
}
