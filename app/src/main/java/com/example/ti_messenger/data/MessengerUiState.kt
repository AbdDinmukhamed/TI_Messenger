package com.example.ti_messenger.data

import androidx.annotation.DrawableRes

data class MessengerUiState(
    val id: Int,
    @DrawableRes val profilePicture: Int,
    val nickname: String
)
