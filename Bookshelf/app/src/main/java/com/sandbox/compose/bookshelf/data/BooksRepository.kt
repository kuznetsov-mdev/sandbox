package com.sandbox.compose.bookshelf.data

import com.sandbox.compose.bookshelf.network.BookService

interface BooksRepository {
    suspend fun getBooks(
        query: String,
        maxResults: Int
    ): List<Book>
}

class NetworkBooksRepository(
    private val booksService: BookService
) : BooksRepository {
    override suspend fun getBooks(query: String, maxResults: Int): List<Book> =
        booksService.searchBook(query, maxResults).items.map { item ->
            Book(
                title = item.volumeInfo?.title,
                prevLink = item.volumeInfo?.previewLink,
                imageLink = item.volumeInfo?.imageLinks?.thumbnail
            )
        }
}