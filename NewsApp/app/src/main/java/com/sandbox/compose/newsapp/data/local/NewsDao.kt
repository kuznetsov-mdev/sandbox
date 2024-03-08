package com.sandbox.compose.newsapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sandbox.compose.newsapp.domain.model.local.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upset(article: ArticleEntity)

    @Delete
    suspend fun delete(article: ArticleEntity)

    @Query("SELECT * FROM ArticleEntity")
    fun getArticles(): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM ArticleEntity WHERE url = :url")
    suspend fun selectArticle(url: String): ArticleEntity?

}