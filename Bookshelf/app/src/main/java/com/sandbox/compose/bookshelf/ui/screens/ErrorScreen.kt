package com.sandbox.compose.bookshelf.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sandbox.compose.bookshelf.R
import com.sandbox.compose.bookshelf.ui.theme.BookshelfTheme

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    retryAction: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.loading_failed))
        Button(
            onClick = retryAction
        ) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    BookshelfTheme {
        ErrorScreen(
            retryAction = {}
        )
    }
}