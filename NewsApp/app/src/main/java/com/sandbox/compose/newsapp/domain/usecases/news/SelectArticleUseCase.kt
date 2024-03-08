package com.sandbox.compose.newsapp.domain.usecases.news

import com.sandbox.compose.newsapp.domain.model.remote.ArticleDto
import com.sandbox.compose.newsapp.domain.repository.NewsRepositoryApi
import com.sandbox.compose.newsapp.util.toDto

class SelectArticleUseCase(
    private val newsRepository: NewsRepositoryApi
) {
    suspend operator fun invoke(articleUrl: String): ArticleDto? {
        return newsRepository.selectArticle(articleUrl)?.toDto()
    }
}