package dev.pimentel.feed.presentation

import dev.pimentel.feed.data.usecases.FetchHeadlines
import dev.pimentel.feed.presentation.FeedContract
import dev.pimentel.feed.presentation.FeedViewModel
import dev.pimentel.feed.shared.ViewModelTest
import dev.pimentel.navigator.FeedNavigator
import io.mockk.*
import org.junit.Assert.assertEquals
import org.junit.Test

class FeedViewModelTest : ViewModelTest<FeedContract.ViewModel>() {

    private val feedNavigator = mockk<FeedNavigator>()
    private val fetchHeadlines = mockk<FetchHeadlines>()

    override lateinit var viewModel: FeedContract.ViewModel

    override fun setupSubject() {
        viewModel = FeedViewModel(
            feedNavigator,
            fetchHeadlines,
            getErrorMessage,
            schedulerProvider
        )
    }

    @Test
    fun `should navigate to profile when testing navigation`() {
        every { feedNavigator.routeToProfile() } just runs

        viewModel.testNavigator()

        verify { feedNavigator.routeToProfile() }
        confirmVerified(feedNavigator, getErrorMessage)
    }

    @Test
    fun `should post message on testText when initializing`() {
        viewModel.initialize()

        assertEquals(viewModel.testText().value, "Teste Fragmento Feed")

        confirmVerified(feedNavigator, getErrorMessage)
    }
}
