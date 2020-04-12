package dev.pimentel.feed.data.usecases

import dev.pimentel.core.abstractions.NoParams
import dev.pimentel.feed.data.entities.Article
import dev.pimentel.feed.data.repository.FeedRepository
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test

class FetchHeadlinesTest {

    private val feedRepository = mockk<FeedRepository>()
    private lateinit var fetchHeadlines: FetchHeadlines

    @Before
    fun setupSubject() {
        fetchHeadlines = FetchHeadlines(feedRepository)
    }

    @Test
    fun `should redirect call to FeedRepository and return an articles list`() {
        val result = listOf(
            Article(
                "author",
                "title",
                "description",
                "url",
                "imageUrl",
                "content"
            )
        )

        every { feedRepository.fetchHeadlines(any()) } returns Single.just(result)

        fetchHeadlines(NoParams)
            .test()
            .assertNoErrors()
            .assertResult(result)

        verify(exactly = 1) { feedRepository.fetchHeadlines(any()) }
        confirmVerified(feedRepository)
    }
}
