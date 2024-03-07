package com.sandbox.compose.newsapp.presentation.bookmark

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.sandbox.compose.newsapp.R
import com.sandbox.compose.newsapp.presentation.Dimens.MediumPadding_24
import com.sandbox.compose.newsapp.presentation.common.ArticleList
import com.sandbox.compose.newsapp.presentation.navgraph.Route
import com.sandbox.compose.newsapp.ui.theme.NewsAppTheme
import com.sandbox.compose.newsapp.util.MockData

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigate: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(start = MediumPadding_24, end = MediumPadding_24, top = MediumPadding_24)
    ) {
        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.text_title)
        )
        Spacer(modifier = Modifier.height(MediumPadding_24))

        ArticleList(articles = state.articles, onClick = { navigate(Route.DetailsScreen.route) })
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun BookmarkScreenPreview() {
    NewsAppTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            BookmarkScreen(
                state = BookmarkState(
                    listOf(
                        MockData.articleDto,
                        MockData.articleDto,
                        MockData.articleDto,
                        MockData.articleDto
                    )
                ),
                navigate = {}
            )
        }
    }

}