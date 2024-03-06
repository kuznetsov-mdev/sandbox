package com.sandbox.compose.newsapp.domain.model.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SourceEntity(
    val id: String,
    val name: String
) : Parcelable