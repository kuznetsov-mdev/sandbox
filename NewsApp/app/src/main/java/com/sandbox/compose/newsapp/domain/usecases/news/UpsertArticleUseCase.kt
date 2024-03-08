package com.sandbox.compose.newsapp.domain.usecases.news

import com.sandbox.compose.newsapp.domain.model.remote.ArticleDto
import com.sandbox.compose.newsapp.domain.repository.NewsRepositoryApi
import com.sandbox.compose.newsapp.util.toEntity

class UpsertArticleUseCase(
    private val newsRepository: NewsRepositoryApi
) {
    suspend operator fun invoke(article: ArticleDto) {
        newsRepository.upsertArticle(article.toEntity())
    }
}