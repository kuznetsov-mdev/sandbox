package com.sandbox.compose.newsapp.domain.usecases.news

import com.sandbox.compose.newsapp.data.local.NewsDao
import com.sandbox.compose.newsapp.domain.model.remote.ArticleDto
import com.sandbox.compose.newsapp.util.toEntity

class UpsertArticleUseCase(
    private val newsDao: NewsDao
) {
    suspend operator fun invoke(article: ArticleDto) {
        newsDao.upset(article.toEntity())
    }
}