package com.sandbox.compose.newsapp.presentation.details

sealed class DetailsEvent {
    data object SaveArticle : DetailsEvent()
}
