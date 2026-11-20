package com.example.ti_messenger.ui.messenger

import androidx.compose.foundation.lazy.items
import com.example.ti_messenger.ui.contacts.ContactsViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ti_messenger.R
import com.example.ti_messenger.data.Contact
import com.example.ti_messenger.data.ContactsRepository
import com.example.ti_messenger.data.MessengerUiState
import com.example.ti_messenger.ui.theme.TI_MessengerTheme


@Composable
fun MessengerScreen(
    modifier: Modifier = Modifier,
    MessengerUiState: MessengerUiState,
    viewModel: ContactsViewModel = viewModel()
){
    val contacts by viewModel.contacts.collectAsStateWithLifecycle()
    Text(text = contacts[0].nickname)

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    TI_MessengerTheme {
        MessengerScreen(modifier = Modifier.statusBarsPadding(),MessengerUiState = MessengerUiState(0,R.drawable.baikalpfp,"Baikal"))
    }
}