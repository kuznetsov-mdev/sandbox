package com.sandbox.compose.newsapp.presentation.onboarding

sealed class OnBoardingEvent {
    object SaveAppEntry : OnBoardingEvent()
}