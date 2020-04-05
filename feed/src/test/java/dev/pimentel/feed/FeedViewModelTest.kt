package dev.pimentel.feed

import dev.pimentel.core.schedulerprovider.SchedulerProvider
import dev.pimentel.core.usecases.GetErrorMessage
import dev.pimentel.feed.presentation.FeedContract
import dev.pimentel.feed.presentation.FeedViewModel
import dev.pimentel.navigator.FeedNavigator
import io.mockk.*
import org.junit.Test

class FeedViewModelTest {

    private val feedNavigator = mockk<FeedNavigator>()
    private val getErrorMessage = mockk<GetErrorMessage>()
    private val schedulerProvider = mockk<SchedulerProvider>()

    private val viewModel: FeedContract.ViewModel = FeedViewModel(
        schedulerProvider,
        getErrorMessage,
        feedNavigator
    )

    @Test
    fun `should navigate to profile when testing navigation`() {
        every { feedNavigator.routeToProfile() } just runs

        viewModel.testNavigator()

        verify { feedNavigator.routeToProfile() }
        confirmVerified(feedNavigator, getErrorMessage, schedulerProvider)
    }
}
