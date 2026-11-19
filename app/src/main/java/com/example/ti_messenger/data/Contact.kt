package com.example.ti_messenger.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Contact(
    @DrawableRes val profilePicture: Int,
    @StringRes val nickname: Int,
    @StringRes val lastMessage: Int,
    @StringRes val lastTime: Int
)
