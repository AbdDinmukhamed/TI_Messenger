package com.example.ti_messenger.data

import androidx.annotation.DrawableRes

data class ChatUiState(
    val id: Int = 0,
    @DrawableRes val profilePicture: Int = 0,
    val nickname: String = "",
    val messagesHistory: List<Message> = listOf(),
    val scrollPos: Int = 0,
)
