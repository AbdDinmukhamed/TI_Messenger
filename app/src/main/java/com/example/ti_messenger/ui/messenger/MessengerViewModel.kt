package com.example.ti_messenger.ui.messenger

import androidx.lifecycle.ViewModel
import com.example.ti_messenger.data.Contact
import com.example.ti_messenger.data.ContactsRepository
import com.example.ti_messenger.data.DemoMessagesRepository
import com.example.ti_messenger.data.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MessengerViewModel : ViewModel() {
    private val _messages = MutableStateFlow(DemoMessagesRepository.messageHistory)
    val messages: StateFlow<List<Message>> = _messages.asStateFlow()
}