package com.sandbox.compose.bookshelf.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sandbox.compose.bookshelf.ui.BooksUiState

@Composable
fun HomeScreen(
    booksUiState: BooksUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (booksUiState) {
        is BooksUiState.Success -> BooksGridScreen(books = booksUiState.bookSearch, modifier = modifier)
        is BooksUiState.Loading -> LoadingScreen(modifier)
        is BooksUiState.Error -> ErrorScreen(retryAction = retryAction, modifier = modifier)
    }
}