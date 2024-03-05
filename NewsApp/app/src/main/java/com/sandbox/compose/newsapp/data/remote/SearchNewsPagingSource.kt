package com.sandbox.compose.newsapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sandbox.compose.newsapp.domain.model.Article

class SearchNewsPagingSource(
    private val newsApi: NewsApi,
    private val searchQuery: String,
    private val sources: String
) : PagingSource<Int, Article>() {

    private var totalNewsCount = 0

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val newsResponse = newsApi.searchNews(searchQuery, page, sources)
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
}