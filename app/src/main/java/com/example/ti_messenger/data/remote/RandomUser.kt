package com.example.ti_messenger.data.remote

import com.google.gson.annotations.SerializedName

data class RandomUserResponse(
    @SerializedName("results")
    val results: List<RandomUser>
)

data class RandomUser(
    val name: UserName,
    val picture: UserPicture
)

data class UserName(
    val title: String,
    val first: String,
    val last: String
)

data class UserPicture(
    val large: String,
    val medium: String,
    val thumbnail: String
)