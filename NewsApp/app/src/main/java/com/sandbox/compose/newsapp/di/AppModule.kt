package com.sandbox.compose.newsapp.di

import android.app.Application
import com.sandbox.compose.newsapp.data.manager.LocalUserManagerImpl
import com.sandbox.compose.newsapp.data.remote.NewsApi
import com.sandbox.compose.newsapp.data.repository.NewsRepositoryImpl
import com.sandbox.compose.newsapp.domain.manager.LocalUserManager
import com.sandbox.compose.newsapp.domain.repository.NewsRepositoryApi
import com.sandbox.compose.newsapp.domain.usecases.appentry.AppEntryUseCases
import com.sandbox.compose.newsapp.domain.usecases.appentry.ReadAppEntry
import com.sandbox.compose.newsapp.domain.usecases.appentry.SaveAppEntry
import com.sandbox.compose.newsapp.domain.usecases.news.GetNewsUseCase
import com.sandbox.compose.newsapp.domain.usecases.news.NewsUseCases
import com.sandbox.compose.newsapp.domain.usecases.news.SearchNewsUseCase
import com.sandbox.compose.newsapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        context: Application
    ): LocalUserManager = LocalUserManagerImpl(context)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(userManager: LocalUserManager): AppEntryUseCases {
        return AppEntryUseCases(
            readAppEntry = ReadAppEntry(userManager),
            saveAppEntry = SaveAppEntry(userManager)
        )
    }

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi): NewsRepositoryApi = NewsRepositoryImpl(newsApi)


    @Provides
    @Singleton
    fun provideNewsUseCases(newsRepository: NewsRepositoryApi): NewsUseCases {
        return NewsUseCases(
            getNews = GetNewsUseCase(newsRepository),
            searchNews = SearchNewsUseCase(newsRepository)
        )
    }
}