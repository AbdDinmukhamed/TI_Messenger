package com.example.ti_messenger.ui.messenger

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ti_messenger.R
import com.example.ti_messenger.data.Message
import com.example.ti_messenger.data.MessengerUiState
import com.example.ti_messenger.ui.theme.TI_MessengerTheme


@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    MessengerUiState: MessengerUiState,
    viewModel: MessengerViewModel = viewModel()
){
    val messages by viewModel.messages.collectAsStateWithLifecycle()



    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){

        items(messages){message ->
            val messageUser : Arrangement.Horizontal = if (message.userId == 0) Arrangement.End else Arrangement.Start
            Row(
                modifier = Modifier.fillMaxWidth().padding(start = 8.dp,end= 8.dp),
                horizontalArrangement = messageUser
            ){
                MessageCard(message)
            }
        }
    }
}


@Composable
fun MessageCard(
    message: Message,
    modifier: Modifier = Modifier
){

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
    ){
        Column(
            modifier = Modifier
                .sizeIn(150.dp,50.dp,200.dp)
                .padding(5.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Text(text = message.text, modifier = Modifier)
            Text(text = message.time, modifier = Modifier.align(Alignment.End))
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    TI_MessengerTheme {
        ChatScreen(modifier = Modifier.statusBarsPadding(),MessengerUiState = MessengerUiState(0,R.drawable.baikalpfp,"Baikal"))
    }
}