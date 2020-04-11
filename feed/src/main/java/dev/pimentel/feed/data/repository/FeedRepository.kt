package dev.pimentel.feed.data.repository

import dev.pimentel.feed.data.entities.Article
import dev.pimentel.feed.data.source.FeedDataSource
import io.reactivex.rxjava3.core.Single

interface FeedRepository {
    fun fetchHeadlines(country: String): Single<List<Article>>
}

internal class FeedRepositoryImpl(
    private val feedDataSource: FeedDataSource
) : FeedRepository {

    override fun fetchHeadlines(country: String): Single<List<Article>> =
        feedDataSource.fetchHeadlines(country).map { response ->
            response.articles.map { articleResponse ->
                Article(
                    articleResponse.author ?: articleResponse.source.name!!,
                    articleResponse.title,
                    articleResponse.description,
                    articleResponse.url,
                    articleResponse.imageUrl,
                    articleResponse.content
                )
            }
        }
}
