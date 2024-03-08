package com.sandbox.compose.newsapp.domain.usecases.news

import com.sandbox.compose.newsapp.domain.model.remote.ArticleDto
import com.sandbox.compose.newsapp.domain.repository.NewsRepositoryApi
import com.sandbox.compose.newsapp.util.toDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSelectedArticlesUseCase(
    private val newsRepository: NewsRepositoryApi
) {
    operator fun invoke(): Flow<List<ArticleDto>> {
        return newsRepository.getArticles().map { articleEntities ->
            articleEntities.map { articleEntity -> articleEntity.toDto() }
        }
    }
}