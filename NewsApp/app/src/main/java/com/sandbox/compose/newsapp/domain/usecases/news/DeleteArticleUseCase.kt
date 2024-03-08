package com.sandbox.compose.newsapp.domain.usecases.news

import com.sandbox.compose.newsapp.domain.model.remote.ArticleDto
import com.sandbox.compose.newsapp.domain.repository.NewsRepositoryApi
import com.sandbox.compose.newsapp.util.toEntity

class DeleteArticleUseCase(
    private val newsRepository: NewsRepositoryApi
) {
    suspend operator fun invoke(article: ArticleDto) {
        newsRepository.deleteArticle(article.toEntity())
    }
}