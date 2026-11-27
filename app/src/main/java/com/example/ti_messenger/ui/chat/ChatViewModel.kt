package com.example.ti_messenger.ui.chat

import androidx.lifecycle.ViewModel
import com.example.ti_messenger.data.ChatUiState
import com.example.ti_messenger.data.DemoMessagesRepository
import com.example.ti_messenger.data.ScrollPositions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ChatViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()


    fun setChat(
        id: Int,
        profilePicture: Int,
        nickname: String,
        ) {
        _uiState.update { currentState ->
            currentState.copy(
                id = id,
                profilePicture = profilePicture,
                nickname = nickname,
                messagesHistory = DemoMessagesRepository.messageHistory[id]!!,
                scrollPos = ScrollPositions.getPosition(id, DemoMessagesRepository.messageHistory[id]!!),
            )
        }
    }

    fun clearMessages(){
        val id = _uiState.value.id
        DemoMessagesRepository.messageHistory[id] = emptyList()
        _uiState.update { currentState ->
            currentState.copy(messagesHistory = emptyList())
        }
    }
}