package com.sandbox.compose.newsapp.domain.repository

import androidx.paging.PagingData
import com.sandbox.compose.newsapp.data.remote.dto.NewsResponse
import kotlinx.coroutines.flow.Flow

interface NewsRepositoryApi {

    fun getNews(sources: List<String>): Flow<PagingData<NewsResponse>>
}