package com.example.ti_messenger

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ti_messenger.data.MessengerUiState
import com.example.ti_messenger.ui.contacts.ContactsViewModel
import com.example.ti_messenger.ui.chat.ChatScreen
import com.example.ti_messenger.ui.chat.ChatViewModel
import com.example.ti_messenger.ui.contacts.ContactsScreen

enum class MessengerScreen(@StringRes val title: Int) {
    Contacts(title = R.string.app_name),
    Messenger(title = R.string.messenger),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessengerAppBar(
    currentScreen: MessengerScreen,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = navigateUp) {
                Image(
                    painter = painterResource(R.drawable.arrow_back),
                    contentDescription = stringResource(R.string.back_button)
                )
            }
        }
    )
}


@Composable
fun MessengerApp(
    contacts: ContactsViewModel = viewModel(),
    messages: ChatViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MessengerScreen.valueOf(
        backStackEntry?.destination?.route ?: MessengerScreen.Contacts.name
    )
    var selectedContact by remember {
        mutableStateOf(
            MessengerUiState(
                id = 0,
                profilePicture = R.drawable.baikalpfp,
                nickname = "Baikal"
            )
        )
    }
    Scaffold(
        topBar = {
            MessengerAppBar(
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
                ContactsScreen(onContactButtonClick = { contact ->
                    selectedContact = MessengerUiState(
                        id = contact.id,
                        profilePicture = contact.profilePicture,
                        nickname = contact.nickname
                    )
                    navController.navigate(MessengerScreen.Messenger.name)
                })
            }
            composable(route = MessengerScreen.Messenger.name) {
                ChatScreen(modifier = Modifier.statusBarsPadding(), MessengerUiState = selectedContact)
            }
        }
    }
}