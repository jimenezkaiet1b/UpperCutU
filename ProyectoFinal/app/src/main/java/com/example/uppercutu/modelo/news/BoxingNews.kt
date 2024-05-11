package com.example.uppercutu.modelo.news

data class BoxingNews(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)