package com.sandbox.compose.newsapp.domain.usecases.news

import com.sandbox.compose.newsapp.data.local.NewsDao
import com.sandbox.compose.newsapp.domain.model.remote.ArticleDto
import com.sandbox.compose.newsapp.util.toDto

class SelectArticleUseCase(
    private val newsDao: NewsDao
) {
    suspend operator fun invoke(articleUrl: String): ArticleDto? {
        return newsDao.selectArticle(articleUrl)?.toDto()
    }
}