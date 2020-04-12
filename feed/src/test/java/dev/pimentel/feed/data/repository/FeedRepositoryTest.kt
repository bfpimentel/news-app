package dev.pimentel.feed.data.repository

import dev.pimentel.feed.data.entities.Article
import dev.pimentel.feed.data.models.HeadlinesResponse
import dev.pimentel.feed.data.repository.FeedRepository
import dev.pimentel.feed.data.repository.FeedRepositoryImpl
import dev.pimentel.feed.data.source.FeedDataSource
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test

class FeedRepositoryTest {

    private val feedDataSource = mockk<FeedDataSource>()
    private lateinit var feedRepository: FeedRepository

    @Before
    fun setupSubject() {
        feedRepository = FeedRepositoryImpl(feedDataSource)
    }

    @Test
    fun `should call feed data source to fetch headlines and map it to articles`() {
        val response = HeadlinesResponse(
            "",
            10,
            listOf(
                HeadlinesResponse.ArticleResponse(
                    HeadlinesResponse.ArticleResponse.SourceResponse(
                        "sourceName1"
                    ),
                    null,
                    "title1",
                    "description1",
                    "url1",
                    "imageUrl1",
                    "content1"
                ),
                HeadlinesResponse.ArticleResponse(
                    HeadlinesResponse.ArticleResponse.SourceResponse(
                        null
                    ),
                    "authorName2",
                    "title2",
                    "description2",
                    "url2",
                    "imageUrl2",
                    null
                )
            )
        )

        val result = listOf(
            Article(
                "sourceName1",
                "title1",
                "description1",
                "url1",
                "imageUrl1",
                "content1"
            ),
            Article(
                "authorName2",
                "title2",
                "description2",
                "url2",
                "imageUrl2",
                null
            )
        )

        every { feedDataSource.fetchHeadlines(any()) } returns Single.just(response)

        feedRepository.fetchHeadlines("")
            .test()
            .assertNoErrors()
            .assertResult(result)

        verify(exactly = 1) { feedDataSource.fetchHeadlines(any()) }
        confirmVerified(feedDataSource)
    }
}
