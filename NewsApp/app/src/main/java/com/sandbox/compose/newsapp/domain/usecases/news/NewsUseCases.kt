package com.sandbox.compose.newsapp.domain.usecases.news

data class NewsUseCases(
    val getNews: GetNewsUseCase,
    val searchNews: SearchNewsUseCase
)
