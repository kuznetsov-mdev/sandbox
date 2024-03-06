package com.sandbox.compose.newsapp.domain.repository

import androidx.paging.PagingData
import com.sandbox.compose.newsapp.domain.model.remote.ArticleDto
import kotlinx.coroutines.flow.Flow

interface NewsRepositoryApi {

    fun getNews(sources: List<String>): Flow<PagingData<ArticleDto>>

    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<ArticleDto>>
}