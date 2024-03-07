package com.sandbox.compose.newsapp.presentation.bookmark

import com.sandbox.compose.newsapp.domain.model.remote.ArticleDto

data class BookmarkState(
    val articles: List<ArticleDto> = emptyList()
)