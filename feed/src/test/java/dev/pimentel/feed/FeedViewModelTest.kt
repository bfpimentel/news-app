package dev.pimentel.feed

import dev.pimentel.feed.shared.ViewModelTest
import dev.pimentel.feed.presentation.FeedContract
import dev.pimentel.feed.presentation.FeedViewModel
import dev.pimentel.navigator.FeedNavigator
import io.mockk.*
import org.junit.Assert.assertEquals
import org.junit.Test

class FeedViewModelTest : ViewModelTest<FeedContract.ViewModel>() {

    private val feedNavigator = mockk<FeedNavigator>()

    override lateinit var viewModel: FeedContract.ViewModel

    override fun setupSubject() {
        viewModel = FeedViewModel(
            schedulerProvider,
            getErrorMessage,
            feedNavigator
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
