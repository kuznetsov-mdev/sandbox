package com.sandbox.compose.newsapp.domain.usecases.news

import androidx.paging.PagingData
import com.sandbox.compose.newsapp.domain.model.remote.ArticleDto
import com.sandbox.compose.newsapp.domain.repository.NewsRepositoryApi
import kotlinx.coroutines.flow.Flow

class GetNewsUseCase(
    private val newsRepository: NewsRepositoryApi
) {
    operator fun invoke(sources: List<String>): Flow<PagingData<ArticleDto>> {
        return newsRepository.getNews(sources)
    }
}