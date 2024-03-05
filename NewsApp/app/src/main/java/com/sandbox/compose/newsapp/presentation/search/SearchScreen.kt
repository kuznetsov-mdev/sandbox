package com.sandbox.compose.newsapp.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.sandbox.compose.newsapp.presentation.Dimens.MediumPadding_24
import com.sandbox.compose.newsapp.presentation.common.ArticleList
import com.sandbox.compose.newsapp.presentation.common.SearchBar
import com.sandbox.compose.newsapp.presentation.navgraph.Route

@Composable
fun SearchScreen(
    state: SearchState,
    searchEvent: (SearchEvent) -> Unit,
    navigate: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                top = MediumPadding_24,
                start = MediumPadding_24,
                end = MediumPadding_24
            )
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { searchEvent(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = { searchEvent(SearchEvent.SearchNews) }
        )
        Spacer(modifier = Modifier.height(MediumPadding_24))
        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticleList(articles = articles, onClick = { navigate(Route.DetailsScreen.route) })
        }
    }
}