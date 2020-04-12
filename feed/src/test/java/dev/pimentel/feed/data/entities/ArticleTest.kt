package dev.pimentel.feed.data.entities

import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class ArticleTest {

    @Test
    fun `should not find null fields`() {
        val article = Article(
            "author",
            "title",
            "description",
            "url",
            "imageUrl",
            "content"
        )

        assertNotNull(article.author)
        assertNotNull(article.title)
        assertNotNull(article.description)
        assertNotNull(article.url)
        assertNotNull(article.imageUrl)
        assertNotNull(article.content)
    }

    @Test
    fun `should find only content null`() {
        val article = Article(
            "author",
            "title",
            "description",
            "url",
            "imageUrl",
            null
        )

        assertNotNull(article.author)
        assertNotNull(article.title)
        assertNotNull(article.description)
        assertNotNull(article.url)
        assertNotNull(article.imageUrl)
        assertNull(article.content)
    }
}
