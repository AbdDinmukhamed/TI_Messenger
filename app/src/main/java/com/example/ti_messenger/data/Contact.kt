package com.example.ti_messenger.data

import androidx.annotation.DrawableRes

data class Contact(
    val id: Int,
    @DrawableRes val profilePicture: Int,
    val nickname: String,
    val lastMessage: String,
    val lastTime: String
)
