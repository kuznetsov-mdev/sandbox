package com.sandbox.compose.newsapp.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

//Todo: create separete models for ui, network, db

@Parcelize
@Entity
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    @PrimaryKey val url: String,
    val urlToImage: String
) : Parcelable