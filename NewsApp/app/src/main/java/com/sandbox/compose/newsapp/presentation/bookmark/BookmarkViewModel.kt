package com.sandbox.compose.newsapp.presentation.bookmark

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandbox.compose.newsapp.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    private val mutableState = mutableStateOf(BookmarkState())
    val state: State<BookmarkState> = mutableState

    init {
        getArticles()
    }

    private fun getArticles() {
        newsUseCases.getSelectedArticlesUseCase().onEach {
            mutableState.value = mutableState.value.copy(articles = it)
        }.launchIn(viewModelScope)
    }
}