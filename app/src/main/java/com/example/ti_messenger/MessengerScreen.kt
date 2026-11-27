package com.example.ti_messenger

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ti_messenger.data.ChatUiState
import com.example.ti_messenger.ui.contacts.ContactsViewModel
import com.example.ti_messenger.ui.chat.ChatScreen
import com.example.ti_messenger.ui.chat.ChatViewModel
import com.example.ti_messenger.ui.contacts.ContactsScreen

enum class MessengerScreen(@StringRes val title: Int) {
    Contacts(title = R.string.app_name),
    Messenger(title = R.string.messenger),
    ContactProfile(title = R.string.profile),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsBar(modifier: Modifier = Modifier){
    TopAppBar(
        title = {Text(stringResource(MessengerScreen.Contacts.title))},
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatBar(
    chatUiState: ChatUiState,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = navigateUp){
                Image(
                    painter = painterResource(R.drawable.arrow_back),
                    contentDescription = stringResource(R.string.back_button)
                )
            }
        },
        title = {
            Row(verticalAlignment = Alignment.CenterVertically){
                Image(
                    painter = painterResource(chatUiState.profilePicture),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                )

                Text(
                    text = chatUiState.nickname,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
    )
}

@Composable
fun MessengerAppBar(
    chatUiState: ChatUiState,
    currentScreen: MessengerScreen,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
){
    when (currentScreen) {
        MessengerScreen.Contacts -> {
            ContactsBar(modifier = modifier)
        }
        MessengerScreen.Messenger -> {
            ChatBar(
                chatUiState = chatUiState,
                navigateUp = navigateUp,
                modifier = modifier
            )
        }
        MessengerScreen.ContactProfile -> {
            //nothing for now
        }
    }
}



@Composable
fun MessengerApp(
    contactsViewModel: ContactsViewModel = viewModel(),
    chatViewModel: ChatViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MessengerScreen.valueOf(
        backStackEntry?.destination?.route ?: MessengerScreen.Contacts.name
    )
    val contacts by contactsViewModel.contacts.collectAsStateWithLifecycle()
    val messages by chatViewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            MessengerAppBar(
                chatUiState = messages,
                currentScreen = currentScreen,
                navigateUp = { navController.navigateUp() }
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
                    contacts = contacts
                )
            }
            composable(route = MessengerScreen.Messenger.name) {
                ChatScreen(
                    modifier = Modifier.statusBarsPadding(),
                    chatHistory = messages.messagesHistory,
                    sendMessage = {}
                )
            }
        }
    }
}