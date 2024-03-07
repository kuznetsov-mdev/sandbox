package com.sandbox.compose.newsapp.util

import com.sandbox.compose.newsapp.domain.model.remote.ArticleDto
import com.sandbox.compose.newsapp.domain.model.remote.SourceDto

object MockData {

    val articleDto = ArticleDto(
        author = "Joel Khalili",
        content = "For eight years, Craig Wright has claimed to be the elusive Bitcoin creator Satoshi Nakamoto. On Monday, in the swelling heat of a UK courtroom, a trial began that will finally settle the question.\\r\\n… [+3163 chars]",
        description = "A UK High Court will settle a long-running debate over whether Craig Wright really is Satoshi Nakamoto, inventor of Bitcoin. Monday’s opening arguments laid the groundwork for both sides.",
        publishedAt = "2024-02-05T21:07:04Z",
        source = SourceDto(
            id = "wired",
            name = "Wired"
        ),
        title = "The Trial Over Bitcoin’s True Creator Is in Session",
        url = "https://www.wired.com/story/craig-wright-bitcoin-satoshi-nakamoto-trial/",
        urlToImage = "https://media.wired.com/photos/65bd7e2524c06ba3ede91a33/191:100/w_1280,c_limit/Craig-Wright-Trial-Day-1-Business-Yellow-1494808061.jpg"
    )
}