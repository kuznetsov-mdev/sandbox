package com.sandbox.compose.newsapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sandbox.compose.newsapp.R
import com.sandbox.compose.newsapp.domain.model.remote.ArticleDto
import com.sandbox.compose.newsapp.presentation.Dimens.ArticleCardSize
import com.sandbox.compose.newsapp.presentation.Dimens.IconSize_11
import com.sandbox.compose.newsapp.presentation.Dimens.SmallPadding_3
import com.sandbox.compose.newsapp.presentation.Dimens.SmallPadding_6
import com.sandbox.compose.newsapp.ui.theme.NewsAppTheme
import com.sandbox.compose.newsapp.util.MockData

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    articleDto: ArticleDto,
    onClick: () -> Unit
) {
    val context = LocalContext.current

    Row(modifier = modifier.clickable { onClick() }) {

        AsyncImage(
            modifier = Modifier
                .size(ArticleCardSize)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(context).data(articleDto.urlToImage).build(),
            contentDescription = "Article image",
            placeholder =
            if (!isSystemInDarkTheme())
                painterResource(id = R.drawable.news_paper)
            else
                painterResource(id = R.drawable.news_paper_night)
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(SmallPadding_3)
                .height(ArticleCardSize)
        ) {
            Text(
                text = articleDto.title,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = R.color.text_title),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = articleDto.source.name,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body),
                )
                Spacer(modifier = Modifier.width(SmallPadding_6))
                Icon(
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = null,
                    modifier = Modifier.size(IconSize_11),
                    tint = colorResource(id = R.color.body)
                )
                Spacer(modifier = Modifier.padding(SmallPadding_6))
                Text(
                    text = articleDto.publishedAt,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardPreview() {
    NewsAppTheme {
        ArticleCard(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            articleDto = MockData.articleDto
        ) {}
    }
}
