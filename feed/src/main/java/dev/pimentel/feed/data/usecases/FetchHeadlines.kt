package dev.pimentel.feed.data.usecases

import dev.pimentel.core.abstractions.NoParams
import dev.pimentel.core.abstractions.UseCase
import dev.pimentel.feed.data.entities.Article
import dev.pimentel.feed.data.repository.FeedRepository
import io.reactivex.rxjava3.core.Single

internal class FetchHeadlines(
    private val feedRepository: FeedRepository
) : UseCase<NoParams, Single<List<Article>>> {

    override fun invoke(params: NoParams): Single<List<Article>> =
        feedRepository.fetchHeadlines("us")
}
