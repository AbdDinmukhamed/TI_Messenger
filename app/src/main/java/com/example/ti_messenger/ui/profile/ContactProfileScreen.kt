package com.example.ti_messenger.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ti_messenger.R
import com.example.ti_messenger.ui.theme.TI_MessengerTheme

@Composable
fun ContactProfileScreen(
    profilePicture: Int,
    nickname: String,
    messageCount: Int,
    onClearChat: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Image(
            painter = painterResource(profilePicture),
            contentDescription = null,
            modifier = Modifier.size(120.dp),
        )
        Text(
            text = nickname,
            style = MaterialTheme.typography.headlineMedium,
        )
        Text(
            text = stringResource(R.string.messages_count, messageCount),
            style = MaterialTheme.typography.bodyMedium,
        )
        Button(onClick = onClearChat) {
            Text(stringResource(R.string.clear_chat))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ContactProfilePreview() {
    TI_MessengerTheme {
        ContactProfileScreen(
            profilePicture = R.drawable.baikalpfp,
            nickname = "Baikal",
            messageCount = 16,
            onClearChat = {},
        )
    }
}