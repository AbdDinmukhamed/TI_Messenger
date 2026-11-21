package com.example.ti_messenger

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ti_messenger.data.MessengerUiState
import com.example.ti_messenger.ui.contacts.ContactsViewModel
import com.example.ti_messenger.ui.chat.ChatScreen
import com.example.ti_messenger.ui.chat.ChatViewModel

enum class MessengerScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
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
    Scaffold(
        topBar = {
            MessengerAppBar(
                currentScreen = MessengerScreen.Messenger,
                navigateUp = { navController.navigateUp() }
            )
        }
    ){ innerPadding ->
        //ContactsScreen(Modifier.padding(innerPadding))
        ChatScreen(modifier = Modifier.padding(innerPadding),MessengerUiState = MessengerUiState(0,R.drawable.baikalpfp,"Baikal"))
    }
}