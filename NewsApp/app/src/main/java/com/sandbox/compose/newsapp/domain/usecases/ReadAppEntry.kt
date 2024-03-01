package com.sandbox.compose.newsapp.domain.usecases

import com.sandbox.compose.newsapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val userManager: LocalUserManager
) {

    operator fun invoke(): Flow<Boolean> {
        return userManager.readAppEntry()
    }
}