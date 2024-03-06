package com.sandbox.compose.newsapp.data.remote.dto

import com.sandbox.compose.newsapp.domain.model.remote.ArticleDto

data class NewsResponse(
    val articleDtos: List<ArticleDto>,
    val status: String,
    val totalResults: Int
)