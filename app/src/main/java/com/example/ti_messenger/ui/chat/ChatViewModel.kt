package com.example.ti_messenger.ui.chat

import androidx.lifecycle.ViewModel
import com.example.ti_messenger.data.ChatUiState
import com.example.ti_messenger.data.DemoMessagesRepository
import com.example.ti_messenger.data.Message
import com.example.ti_messenger.data.ScrollPositions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Calendar

class ChatViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    fun getAllMessages() : MutableMap<Int,List<Message>>{
        return DemoMessagesRepository.messageHistory
    }
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
                messagesHistory = DemoMessagesRepository.messageHistory[id].orEmpty(),
                scrollPos = getScrollPosition(id, DemoMessagesRepository.messageHistory[id].orEmpty()),
            )
        }
    }

    fun sendMessage(message: String){
        if (message == "") return

        _uiState.update { currentState ->
            val chatId = currentState.id

            val newMessage = Message(
                text = message,
                time = currentTime(),
                userId = 0
            )

            val updatedHistory = DemoMessagesRepository.messageHistory[chatId].orEmpty() + newMessage
            DemoMessagesRepository.messageHistory[chatId] = updatedHistory

            currentState.copy(
                messagesHistory = updatedHistory,
                scrollPos = updatedHistory.size,
            )
        }
    }

    fun getScrollPosition(id: Int, messages: List<Message>): Int{
        val savedValue = ScrollPositions.scrollPositions[id]
        if (savedValue == null){
            ScrollPositions.scrollPositions[id] = messages.size
        }
        return ScrollPositions.scrollPositions[id]!!
    }
    fun saveScrollPosition(id: Int, position: Int){
        ScrollPositions.scrollPositions[id] = position
    }

    fun clearMessages(){
        val id = _uiState.value.id
        DemoMessagesRepository.messageHistory[id] = emptyList()
        _uiState.update { currentState ->
            currentState.copy(messagesHistory = emptyList())
        }
    }

    fun currentTime(): String{
        val calendar = Calendar.getInstance()
        return "${calendar.get(Calendar.HOUR)}:${calendar.get(Calendar.MINUTE)}"
    }
}