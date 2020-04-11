package dev.pimentel.feed.data.source

import dev.pimentel.feed.data.models.HeadlinesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FeedDataSource {

    @GET("/v2/top-headlines")
    fun fetchHeadlines(@Query("country") country: String): Single<HeadlinesResponse>
}
