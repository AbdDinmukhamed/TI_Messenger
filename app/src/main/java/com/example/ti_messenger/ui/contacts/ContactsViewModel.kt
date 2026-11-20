package com.example.ti_messenger.ui.contacts

import androidx.lifecycle.ViewModel
import com.example.ti_messenger.data.Contact
import com.example.ti_messenger.data.ContactsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ContactsViewModel : ViewModel() {
    private val _contacts = MutableStateFlow(ContactsRepository.contacts)
    val contacts: StateFlow<List<Contact>> = _contacts.asStateFlow()
}