package com.sandbox.compose.newsapp.domain.usecases.appentry

import com.sandbox.compose.newsapp.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}