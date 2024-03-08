package com.sandbox.compose.newsapp.domain.repository

import androidx.paging.PagingData
import com.sandbox.compose.newsapp.domain.model.local.ArticleEntity
import com.sandbox.compose.newsapp.domain.model.remote.ArticleDto
import kotlinx.coroutines.flow.Flow

interface NewsRepositoryApi {

    fun getNews(sources: List<String>): Flow<PagingData<ArticleDto>>

    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<ArticleDto>>

    suspend fun upsertArticle(article: ArticleEntity)

    suspend fun deleteArticle(article: ArticleEntity)

    fun getArticles(): Flow<List<ArticleEntity>>

    suspend fun selectArticle(articleUrl: String): ArticleEntity?
}