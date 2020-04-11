package dev.pimentel.feed.data.entities

data class Article(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val imageUrl: String,
    val content: String?
)
