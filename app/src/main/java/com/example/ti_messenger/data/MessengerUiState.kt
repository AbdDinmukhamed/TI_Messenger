package com.example.ti_messenger.data

import androidx.annotation.DrawableRes

data class MessengerUiState(
    val id: Int = 0,
    @DrawableRes val profilePicture: Int = 0,
    val nickname: String = "",
    val messagesHistory: List<Message> = listOf(),
)
