package com.sandbox.compose.bookshelf

import android.app.Application
import com.sandbox.compose.bookshelf.data.AppContainer
import com.sandbox.compose.bookshelf.data.DefaultAppContainer

class BooksApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}