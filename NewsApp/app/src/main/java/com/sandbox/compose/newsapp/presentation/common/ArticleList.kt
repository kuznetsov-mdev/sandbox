package com.sandbox.compose.newsapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.sandbox.compose.newsapp.domain.model.remote.ArticleDto
import com.sandbox.compose.newsapp.presentation.Dimens.MediumPadding_24
import com.sandbox.compose.newsapp.presentation.Dimens.SmallPadding_3

@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<ArticleDto>,
    onClick: (ArticleDto) -> Unit
) {
    val handlePagingResult = handlePagingResult(articles = articles)
    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding_24),
            contentPadding = PaddingValues(all = SmallPadding_3)
        ) {
            items(count = articles.itemCount) {
                articles[it]?.let { article ->
                    ArticleCard(articleDto = article, onClick = { onClick(article) })
                }
            }
        }
    }
}

@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    articles: List<ArticleDto>,
    onClick: (ArticleDto) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MediumPadding_24),
        contentPadding = PaddingValues(all = SmallPadding_3)
    ) {
        items(count = articles.size) {
            val articles = articles[it]
            ArticleCard(articleDto = articles, onClick = { onClick(articles) })

        }
    }
}

@Composable
fun handlePagingResult(
    articles: LazyPagingItems<ArticleDto>
): Boolean {
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            return false
        }

        error != null -> {
            EmptyScreen()
            false
        }

        else -> true
    }
}

@Composable
private fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(SmallPadding_3)) {
        repeat(10) {
            ArticleCardShimmerEffect(modifier = Modifier.padding(horizontal = SmallPadding_3))
        }
    }
}