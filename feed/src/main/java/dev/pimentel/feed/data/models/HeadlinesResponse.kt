package dev.pimentel.feed.data.models

import com.google.gson.annotations.SerializedName

data class HeadlinesResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleResponse>
) {

    data class ArticleResponse(
        val source: SourceResponse,
        val author: String?,
        val title: String,
        val description: String,
        val url: String,
        @SerializedName("urlToImage") val imageUrl: String,
        val content: String?
    ) {

        data class SourceResponse(
            val name: String?
        )
    }
}
