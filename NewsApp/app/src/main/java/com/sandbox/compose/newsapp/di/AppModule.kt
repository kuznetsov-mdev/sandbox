package com.sandbox.compose.newsapp.di

import android.app.Application
import com.sandbox.compose.newsapp.data.manager.LocalUserManagerImpl
import com.sandbox.compose.newsapp.domain.manager.LocalUserManager
import com.sandbox.compose.newsapp.domain.usecases.appentry.AppEntryUseCases
import com.sandbox.compose.newsapp.domain.usecases.appentry.ReadAppEntry
import com.sandbox.compose.newsapp.domain.usecases.appentry.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
}