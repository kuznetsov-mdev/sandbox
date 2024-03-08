package com.sandbox.compose.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sandbox.compose.newsapp.domain.model.local.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 2)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract val newsDao: NewsDao
}