package dev.pimentel.feed.data.models

import dev.pimentel.feed.data.models.HeadlinesResponse.ArticleResponse
import dev.pimentel.feed.data.models.HeadlinesResponse.ArticleResponse.SourceResponse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class HeadlinesResponseTest {

    @Test
    fun `should not find null fields on HeadlinesResponse`() {
        val headlinesResponse = HeadlinesResponse(
            "status",
            10,
            listOf(
                ArticleResponse(
                    SourceResponse(
                        "sourceName"
                    ),
                    "author",
                    "title",
                    "description",
                    "url",
                    "imageUrl",
                    "content"
                )
            )
        )

        assertNotNull(headlinesResponse.status)
        assertNotNull(headlinesResponse.totalResults)

        val article = headlinesResponse.articles.first()

        assertNotNull(article.source.name)
        assertNotNull(article.author)
        assertNotNull(article.title)
        assertNotNull(article.description)
        assertNotNull(article.url)
        assertNotNull(article.imageUrl)
        assertNotNull(article.content)
    }

    @Test
    fun `should find null fields on HeadlinesResponse`() {
        val headlinesResponse = HeadlinesResponse(
            "status",
            10,
            listOf(
                ArticleResponse(
                    SourceResponse(
                        null
                    ),
                    null,
                    "title",
                    "description",
                    "url",
                    "imageUrl",
                    null
                )
            )
        )

        assertNotNull(headlinesResponse.status)
        assertNotNull(headlinesResponse.totalResults)

        val article = headlinesResponse.articles.first()

        assertNull(article.source.name)
        assertNull(article.author)
        assertNotNull(article.title)
        assertNotNull(article.description)
        assertNotNull(article.url)
        assertNotNull(article.imageUrl)
        assertNull(article.content)
    }
}
