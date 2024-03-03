package com.sandbox.compose.newsapp.data.remote.dto

import com.sandbox.compose.newsapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)