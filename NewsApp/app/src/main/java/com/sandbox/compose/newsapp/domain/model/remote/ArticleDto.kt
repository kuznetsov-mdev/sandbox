package com.sandbox.compose.newsapp.domain.model.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleDto(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: SourceDto,
    val title: String,
    val url: String,
    val urlToImage: String
) : Parcelable