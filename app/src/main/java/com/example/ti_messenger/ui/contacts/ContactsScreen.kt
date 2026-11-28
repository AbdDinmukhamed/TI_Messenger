package com.example.ti_messenger.ui.contacts

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ti_messenger.data.Contact
import com.example.ti_messenger.data.Message
import com.example.ti_messenger.data.remote.RandomUsersUiState
import com.example.ti_messenger.ui.theme.TI_MessengerTheme


@Composable
fun ContactsScreen(
    modifier: Modifier = Modifier,
    contacts: List<Contact> = listOf(),
    onContactButtonClick: (Contact) -> Unit,
    messagesDB: MutableMap<Int,List<Message>>,
    randomUsersState: RandomUsersUiState = RandomUsersUiState.Loading,
    onRetryRandomUsers: () -> Unit = {}
){
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        item{
            DiscoverPeopleSection(uiState = randomUsersState, onRetry = onRetryRandomUsers)
        }
        items(contacts){contact ->
            ContactCard(contact, onContactButtonClick = onContactButtonClick,messagesDB = messagesDB)
        }
    }

}

@Composable
fun ContactCard(
    contact: Contact,
    modifier: Modifier = Modifier,
    onContactButtonClick: (Contact) -> Unit,
    messagesDB: MutableMap<Int,List<Message>>
){
    val message = messagesDB[contact.id]?.lastOrNull() ?: Message("say hi to them","",0)
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
        onClick = { onContactButtonClick(contact) }
    ){
        Row(){
            Image(
                painter = painterResource(contact.profilePicture),
                contentDescription = null,
                modifier = Modifier.size(64.dp).padding(4.dp),
            )
            Column(
                modifier = Modifier.height(64.dp).padding(start = 4.dp, end = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ){
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(text = contact.nickname)
                    Text(text = message.time)
                }
                Text(text = message.text, modifier = Modifier.padding(bottom = 4.dp))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    TI_MessengerTheme {
        ContactsScreen(modifier = Modifier.statusBarsPadding(), onContactButtonClick = {},messagesDB=mutableMapOf())
    }
}