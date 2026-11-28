package com.example.ti_messenger.ui.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ti_messenger.data.remote.RandomUsersRepository
import com.example.ti_messenger.data.remote.RandomUsersUiState
import com.example.ti_messenger.data.remote.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RandomUsersViewModel: ViewModel() {
    private val repository = RandomUsersRepository(RetrofitClient.api)

    private val _uiState = MutableStateFlow<RandomUsersUiState>(RandomUsersUiState.Loading)
    val uiState: StateFlow<RandomUsersUiState> = _uiState.asStateFlow()

    init {
        loadUsers()
    }

    fun loadUsers() {
        viewModelScope.launch {
            _uiState.value = RandomUsersUiState.Loading

            try {
                val users = repository.getUsers()

                if (users.isEmpty()) {
                    _uiState.value = RandomUsersUiState.Empty
                }
                else {
                    _uiState.value = RandomUsersUiState.Success(users)
                }
            }
            catch (e: Exception) {
                _uiState.value = RandomUsersUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}