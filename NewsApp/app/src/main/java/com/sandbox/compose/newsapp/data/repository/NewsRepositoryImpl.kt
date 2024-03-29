package com.sandbox.compose.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sandbox.compose.newsapp.data.local.NewsDao
import com.sandbox.compose.newsapp.data.remote.NewsApi
import com.sandbox.compose.newsapp.data.remote.NewsPagingSource
import com.sandbox.compose.newsapp.data.remote.SearchNewsPagingSource
import com.sandbox.compose.newsapp.domain.model.local.ArticleEntity
import com.sandbox.compose.newsapp.domain.model.remote.ArticleDto
import com.sandbox.compose.newsapp.domain.repository.NewsRepositoryApi
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao
) : NewsRepositoryApi {

    override fun getNews(sources: List<String>): Flow<PagingData<ArticleDto>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(",")
                )
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<ArticleDto>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    searchQuery = searchQuery,
                    newsApi = newsApi,
                    sources = sources.joinToString(",")
                )
            }
        ).flow
    }

    override suspend fun upsertArticle(article: ArticleEntity) {
        newsDao.upset(article = article)
    }

    override suspend fun deleteArticle(article: ArticleEntity) {
        newsDao.delete(article = article)
    }

    override fun getArticles(): Flow<List<ArticleEntity>> {
        return newsDao.getArticles()
    }

    override suspend fun selectArticle(articleUrl: String): ArticleEntity? {
        return newsDao.selectArticle(articleUrl)
    }
}