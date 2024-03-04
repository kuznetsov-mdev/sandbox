package com.sandbox.compose.newsapp.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.sandbox.compose.newsapp.R
import com.sandbox.compose.newsapp.domain.model.Article
import com.sandbox.compose.newsapp.presentation.Dimens.MediumPadding_24
import com.sandbox.compose.newsapp.presentation.common.ArticleList
import com.sandbox.compose.newsapp.presentation.common.SearchBar
import com.sandbox.compose.newsapp.presentation.navgraph.Route

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigate: (String) -> Unit
) {
    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " \uD83d\uDFE5 ") { it.title }
            } else {
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MediumPadding_24)
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = MediumPadding_24)
        )
    }
    Spacer(modifier = Modifier.height(MediumPadding_24))

    SearchBar(
        modifier = Modifier.padding(horizontal = MediumPadding_24),
        text = "",
        readOnly = true,
        onValueChange = {},
        onClick = {
            navigate(Route.SearchScreen.route)
        },
        onSearch = {}
    )

    Spacer(modifier = Modifier.padding(MediumPadding_24))

    Text(
        text = titles,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = MediumPadding_24)
            .basicMarquee(),
        fontSize = 12.sp,
        color = colorResource(id = R.color.placeholder)
    )

    Spacer(modifier = Modifier.padding(MediumPadding_24))

    ArticleList(
        modifier = Modifier.padding(horizontal = MediumPadding_24),
        articles = articles,
        onClick = {
            navigate(Route.DetailsScreen.route)
        }
    )
}