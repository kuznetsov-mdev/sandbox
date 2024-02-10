package com.sandbox.compose.bookshelf.network

import com.sandbox.compose.bookshelf.Bookshelf
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {
    @GET("volumes")
    suspend fun searchBook(
        @Query("q") searchQuery: String,
        @Query("maxResults") maxResults: Int
    ): Bookshelf
}