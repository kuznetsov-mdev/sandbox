package com.sandbox.compose.newsapp.domain.usecases.news

import androidx.paging.PagingData
import com.sandbox.compose.newsapp.domain.model.Article
import com.sandbox.compose.newsapp.domain.repository.NewsRepositoryApi
import kotlinx.coroutines.flow.Flow

class SearchNewsUseCase(
    private val newsRepository: NewsRepositoryApi
) {
    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.searchNews(searchQuery, sources)
    }
}