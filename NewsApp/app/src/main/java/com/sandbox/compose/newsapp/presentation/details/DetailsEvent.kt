package com.sandbox.compose.newsapp.presentation.details

import com.sandbox.compose.newsapp.domain.model.remote.ArticleDto

sealed class DetailsEvent {
    data class UpsertDeleteArticle(val article: ArticleDto) : DetailsEvent()
    data object RemoveSideEffect : DetailsEvent()
}
