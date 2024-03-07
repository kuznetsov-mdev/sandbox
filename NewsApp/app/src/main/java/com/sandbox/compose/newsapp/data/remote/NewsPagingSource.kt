package com.sandbox.compose.newsapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sandbox.compose.newsapp.domain.model.remote.ArticleDto

class NewsPagingSource(
    private val newsApi: NewsApi,
    private val sources: String
) : PagingSource<Int, ArticleDto>() {

    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleDto> {
        val page = params.key ?: 1
        return try {
            val newsResponse = newsApi.getNews(page, sources)
            totalNewsCount += newsResponse.articles.size
            LoadResult.Page(
                data = newsResponse.articles.distinctBy { it.title }, //remove duplicates articles
                prevKey = null,
                nextKey = if (totalNewsCount == newsResponse.totalResults) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArticleDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }


}