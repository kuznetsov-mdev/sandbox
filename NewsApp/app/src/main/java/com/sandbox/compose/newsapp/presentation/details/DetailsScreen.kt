package com.sandbox.compose.newsapp.presentation.details

import android.content.Context
import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sandbox.compose.newsapp.R
import com.sandbox.compose.newsapp.domain.model.Article
import com.sandbox.compose.newsapp.domain.model.Source
import com.sandbox.compose.newsapp.presentation.Dimens.ImageHeight_248
import com.sandbox.compose.newsapp.presentation.Dimens.MediumPadding_24
import com.sandbox.compose.newsapp.presentation.details.components.DetailsTopBar
import com.sandbox.compose.newsapp.ui.theme.NewsAppTheme

@Composable
fun DetailsScreen(
    article: Article,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(
            onBrowserClick = { openArticleInBrowser(article.url, context) },
            onShareClick = { shareArticle(article.url, context) },
            onBookmarkClick = { event(DetailsEvent.SaveArticle) },
            onBackClick = navigateUp
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = MediumPadding_24,
                end = MediumPadding_24,
                top = MediumPadding_24
            )
        ) {
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(article.urlToImage).build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ImageHeight_248)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(MediumPadding_24))
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(id = R.color.text_title)
                )

                Text(
                    text = article.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(id = R.color.body)
                )
            }
        }
    }
}

private fun openArticleInBrowser(articleUrl: String, context: Context) {
    Intent(Intent.ACTION_VIEW).also {
        it.data = Uri.parse(articleUrl)
        //Check if is there app can handle this action
        if (it.resolveActivity(context.packageManager) != null) {
            context.startActivity(it)
        }
    }
}

private fun shareArticle(articleUrl: String, context: Context) {
    Intent(Intent.ACTION_SEND).also {
        it.putExtra(Intent.EXTRA_TEXT, articleUrl)
        it.type = "text/plain"
        if (it.resolveActivity(context.packageManager) != null) {
            context.startActivity(it)
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DetailsScreenPreview() {
    NewsAppTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            DetailsScreen(
                article = Article(
                    author = "Joel Khalili",
                    content = "For eight years, Craig Wright has claimed to be the elusive Bitcoin creator Satoshi Nakamoto. On Monday, in the swelling heat of a UK courtroom, a trial began that will finally settle the question.\\r\\n… [+3163 chars]",
                    description = "A UK High Court will settle a long-running debate over whether Craig Wright really is Satoshi Nakamoto, inventor of Bitcoin. Monday’s opening arguments laid the groundwork for both sides.",
                    publishedAt = "2024-02-05T21:07:04Z",
                    source = Source(
                        id = "wired",
                        name = "Wired"
                    ),
                    title = "The Trial Over Bitcoin’s True Creator Is in Session",
                    url = "https://www.wired.com/story/craig-wright-bitcoin-satoshi-nakamoto-trial/",
                    urlToImage = "https://media.wired.com/photos/65bd7e2524c06ba3ede91a33/191:100/w_1280,c_limit/Craig-Wright-Trial-Day-1-Business-Yellow-1494808061.jpg"
                ),
                event = {},
                navigateUp = {}
            )
        }
    }
}
