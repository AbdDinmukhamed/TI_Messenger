package com.example.ti_messenger.data.remote

import com.google.gson.annotations.SerializedName

data class RandomUserResponse(
    @SerializedName("results")
    val results: List<RandomUser>
)

data class RandomUser(
    val name: RandomUsername,
    val picture: RandomUserPicture
)

data class RandomUsername(
    val title: String,
    val first: String,
    val last: String
)

data class RandomUserPicture(
    val large: String,
    val medium: String,
    val thumbnail: String
)