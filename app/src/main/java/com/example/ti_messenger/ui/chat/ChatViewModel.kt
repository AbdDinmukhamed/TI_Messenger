package com.example.ti_messenger.ui.chat

import androidx.lifecycle.ViewModel
import com.example.ti_messenger.data.DemoMessagesRepository
import com.example.ti_messenger.data.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChatViewModel : ViewModel() {
    private val _messages = MutableStateFlow(DemoMessagesRepository.messageHistory)
    val messages: StateFlow<List<Message>> = _messages.asStateFlow()
}