@file:Suppress("UNUSED_EXPRESSION")

package com.sandbox.compose.bookshelf.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sandbox.compose.bookshelf.data.Book
import com.sandbox.compose.bookshelf.ui.screens.HomeScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksApp(
    modifier: Modifier = Modifier,
    onBookClicked: (Book) -> Unit
) {
    val viewModel: BooksViewModel = viewModel(factory = BooksViewModel.Factory)
    val searchWidgetState = viewModel.searchWidgetState
    val searchTextState = viewModel.searchTextState

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            MainAppBar(
                searchWidgetState = searchWidgetState.value,
                searchTextState = searchTextState.value,
                onTextChanged = { viewModel.updateSearchTextState(it) },
                onCloseClicked = { viewModel.updateWidgetState(SearchWidgetState.CLOSED) },
                onSearchClicked = { viewModel.getBooks(it) },
                onSearchTriggered = { viewModel.updateWidgetState(SearchWidgetState.OPENED) }
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen(
                booksUiState = viewModel.booksUiState,
                retryAction = { viewModel::getBooks },
                onBookClicked = onBookClicked
            )
        }
    }
}