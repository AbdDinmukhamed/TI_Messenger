package com.example.ti_messenger

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ti_messenger.data.Message
import com.example.ti_messenger.ui.appbar.MessengerAppBar
import com.example.ti_messenger.ui.contacts.ContactsViewModel
import com.example.ti_messenger.ui.chat.ChatScreen
import com.example.ti_messenger.ui.chat.ChatViewModel
import com.example.ti_messenger.ui.contacts.ContactsScreen
import com.example.ti_messenger.ui.contacts.RandomUsersViewModel
import com.example.ti_messenger.ui.profile.ContactProfileScreen
import com.example.ti_messenger.ui.settings.SettingsScreen

enum class MessengerScreen(@StringRes val title: Int) {
    Contacts(title = R.string.app_name),
    Messenger(title = R.string.messenger),
    ContactProfile(title = R.string.profile),
    Settings(title = R.string.settings)
}

@Composable
fun MessengerApp(
    contactsViewModel: ContactsViewModel = viewModel(),
    chatViewModel: ChatViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    onThemeSwitch: () -> Unit
    randomUsersViewModel: RandomUsersViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MessengerScreen.valueOf(
        backStackEntry?.destination?.route ?: MessengerScreen.Contacts.name
    )
    val contacts by contactsViewModel.contacts.collectAsStateWithLifecycle()
    val messages by chatViewModel.uiState.collectAsStateWithLifecycle()
    val randomUsersState by randomUsersViewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            MessengerAppBar(
                chatUiState = messages,
                currentScreen = currentScreen,
                navigateUp = { navController.navigateUp() },
                onChatTitleClick = {
                    if (currentScreen == MessengerScreen.Messenger) {
                        navController.navigate(MessengerScreen.ContactProfile.name)
                    }
                },
                navigateSettings = {
                    navController.navigate(MessengerScreen.Settings.name)
                }
            )
        }
    ){ innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MessengerScreen.Contacts.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = MessengerScreen.Contacts.name) {
                ContactsScreen(
                    onContactButtonClick = { contact ->
                        chatViewModel.setChat(
                            id = contact.id,
                            profilePicture = contact.profilePicture,
                            nickname = contact.nickname)
                        navController.navigate(MessengerScreen.Messenger.name)
                    },
                    contacts = contacts,
                    messagesDB = chatViewModel.getAllMessages(),
                    randomUsersState = randomUsersState,
                    onRetryRandomUsers = {randomUsersViewModel.loadUsers()}
                )
            }
            composable(route = MessengerScreen.Messenger.name) {
                ChatScreen(
                    modifier = Modifier.statusBarsPadding(),
                    chatHistory = messages.messagesHistory,
                    sendMessage = {text -> chatViewModel.sendMessage(text)},
                    scrollPosition = messages.scrollPos,
                    onSaveScrollPos = {
                        chatViewModel.saveScrollPosition(
                            id = messages.id,
                            position = it)
                    }
                )
            }
            composable(route = MessengerScreen.ContactProfile.name) {
                ContactProfileScreen(
                    profilePicture = messages.profilePicture,
                    nickname = messages.nickname,
                    messageCount = messages.messagesHistory.size,
                    onClearChat = {
                        chatViewModel.clearMessages()
                        navController.navigateUp()
                    },
                )
            }
            composable(route = MessengerScreen.Settings.name) {
                SettingsScreen(onThemeSwitch = onThemeSwitch)
            }
        }
    }
}