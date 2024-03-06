package com.sandbox.compose.newsapp.data.local

import androidx.paging.PagingData
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
    suspend fun upset(articleDto: ArticleEntity)

    @Delete
    suspend fun delete(articleDto: ArticleEntity)

    @Query("SELECT * FROM ArticleEntity")
    fun getArticles(): Flow<PagingData<ArticleEntity>>

}