package com.example.ti_messenger.data.remote

class RandomUsersRepository(
    private val api: RandomUserApi
) {
    suspend fun getUsers(): List<RandomUser> {
        return api.getUsers(5).results
    }
}