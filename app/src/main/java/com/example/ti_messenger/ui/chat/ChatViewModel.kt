package com.example.ti_messenger.ui.chat

import androidx.lifecycle.ViewModel
import com.example.ti_messenger.data.DemoMessagesRepository
import com.example.ti_messenger.data.Message
import com.example.ti_messenger.data.MessengerUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChatViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MessengerUiState())
    val uiState: StateFlow<MessengerUiState> = _uiState.asStateFlow()
}