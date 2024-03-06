package com.sandbox.compose.newsapp.domain.model.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class ArticleEntity(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: SourceEntity,
    val title: String,
    @PrimaryKey val url: String,
    val urlToImage: String
) : Parcelable