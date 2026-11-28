package com.example.ti_messenger.ui.appbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ti_messenger.MessengerScreen
import com.example.ti_messenger.R
import com.example.ti_messenger.data.ChatUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsBar(modifier: Modifier = Modifier,
                navigateSettings: () -> Unit){
    TopAppBar(
        title = {Text(stringResource(MessengerScreen.Contacts.title))},
        modifier = modifier,
        actions = {
            IconButton(onClick = navigateSettings){
                Image(
                    painter = painterResource(R.drawable.baseline_settings_24),
                    contentDescription = stringResource(R.string.settings)
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsBar(modifier: Modifier = Modifier,
                navigateUp: () -> Unit){
    TopAppBar(
        title = {Text(stringResource(R.string.settings))},
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = navigateUp){
                Image(
                    painter = painterResource(R.drawable.arrow_back),
                    contentDescription = stringResource(R.string.settings)
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatBar(
    chatUiState: ChatUiState,
    navigateUp: () -> Unit,
    onTitleClick: () -> Unit,
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable(onClick = onTitleClick)
            ){
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactProfileBar(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = navigateUp) {
                Image(
                    painter = painterResource(R.drawable.arrow_back),
                    contentDescription = stringResource(R.string.back_button)
                )
            }
        },
        title = { Text(stringResource(MessengerScreen.ContactProfile.title)) },
    )
}

@Composable
fun MessengerAppBar(
    chatUiState: ChatUiState,
    currentScreen: MessengerScreen,
    navigateUp: () -> Unit,
    onChatTitleClick: () -> Unit,
    navigateSettings: () -> Unit,
    modifier: Modifier = Modifier
){
    when (currentScreen) {
        MessengerScreen.Contacts -> ContactsBar(modifier = modifier, navigateSettings)

        MessengerScreen.Messenger -> ChatBar(
                chatUiState = chatUiState,
                navigateUp = navigateUp,
                modifier = modifier,
                onTitleClick = onChatTitleClick
            )

        MessengerScreen.ContactProfile -> ContactProfileBar(
            navigateUp = navigateUp,
            modifier = modifier,
        )

        MessengerScreen.Settings -> SettingsBar(modifier = modifier, navigateUp)

    }
}