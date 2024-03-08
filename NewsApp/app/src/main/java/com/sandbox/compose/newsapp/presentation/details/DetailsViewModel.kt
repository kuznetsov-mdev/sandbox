package com.sandbox.compose.newsapp.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandbox.compose.newsapp.domain.model.remote.ArticleDto
import com.sandbox.compose.newsapp.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    var sideEffect by mutableStateOf<String?>(null)
        private set

    fun onEvent(detailsEvent: DetailsEvent) {
        when (detailsEvent) {
            is DetailsEvent.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = newsUseCases.selectArticleUseCase(detailsEvent.article.url)
                    if (article == null) {
                        upsertArticle(detailsEvent.article)
                    } else {
                        deleteArticle(detailsEvent.article)
                    }
                }
            }

            is DetailsEvent.RemoveSideEffect -> {
                sideEffect = null
            }
        }
    }

    private suspend fun upsertArticle(article: ArticleDto) {
        newsUseCases.upsertNewsUseCase.invoke(article)
        sideEffect = "Article saved"
    }

    private suspend fun deleteArticle(article: ArticleDto) {
        newsUseCases.deleteArticleUseCase.invoke(article)
        sideEffect = "Article deleted"
    }

}