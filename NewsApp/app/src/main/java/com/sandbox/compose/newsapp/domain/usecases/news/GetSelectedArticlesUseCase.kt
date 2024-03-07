package com.sandbox.compose.newsapp.domain.usecases.news

import com.sandbox.compose.newsapp.data.local.NewsDao
import com.sandbox.compose.newsapp.domain.model.remote.ArticleDto
import com.sandbox.compose.newsapp.util.toDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSelectedArticlesUseCase(
    private val newsDao: NewsDao
) {
    suspend operator fun invoke(): Flow<List<ArticleDto>> {
        return newsDao.getArticles().map { articleEntities ->
            articleEntities.map { articleEntity -> articleEntity.toDto() }
        }
    }
}