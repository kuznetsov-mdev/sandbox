package com.sandbox.compose.newsapp.util

import com.sandbox.compose.newsapp.domain.model.local.ArticleEntity
import com.sandbox.compose.newsapp.domain.model.local.SourceEntity
import com.sandbox.compose.newsapp.domain.model.remote.ArticleDto
import com.sandbox.compose.newsapp.domain.model.remote.SourceDto


fun ArticleDto.toEntity(): ArticleEntity {
    return ArticleEntity(
        author = this.author,
        content = this.content,
        description = this.description,
        publishedAt = this.publishedAt,
        source = SourceEntity(
            id = this.source.id,
            name = this.source.name
        ),
        title = this.title,
        url = this.url,
        urlToImage = this.urlToImage
    )
}

fun ArticleEntity.toDto(): ArticleDto {
    return ArticleDto(
        author = this.author ?: "",
        content = this.content,
        description = this.description,
        publishedAt = this.publishedAt,
        source = SourceDto(
            id = this.source.id,
            name = this.source.name
        ),
        title = this.title,
        url = this.url,
        urlToImage = this.urlToImage
    )
}