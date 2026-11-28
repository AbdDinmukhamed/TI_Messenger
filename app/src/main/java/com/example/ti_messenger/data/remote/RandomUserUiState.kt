package com.example.ti_messenger.data.remote

sealed interface RandomUsersUiState {
    data object Loading: RandomUsersUiState
    data class Success(val users: List<RandomUser>): RandomUsersUiState
    data class Error(val message: String): RandomUsersUiState
    data object Empty : RandomUsersUiState
}