package com.sandbox.compose.newsapp.domain.model.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SourceDto(
    val id: String,
    val name: String
) : Parcelable