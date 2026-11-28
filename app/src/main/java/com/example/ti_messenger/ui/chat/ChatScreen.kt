package com.example.ti_messenger.ui.chat

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ti_messenger.R
import com.example.ti_messenger.data.Message
import com.example.ti_messenger.data.ChatUiState
import com.example.ti_messenger.ui.theme.TI_MessengerTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    chatHistory: List<Message> = listOf(),
    sendMessage: (String) -> Unit,
    scrollPosition: Int,
    onSaveScrollPos: (Int) -> Unit,
){
    var messageInput by remember { mutableStateOf("") }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val isFocused by listState.interactionSource.collectIsDraggedAsState()
    LaunchedEffect(!isFocused) {
        onSaveScrollPos(listState.firstVisibleItemIndex)
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .imePadding()){
        LazyColumn(
            modifier = modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            state = listState
        ){
            coroutineScope.launch {
                listState.scrollToItem(scrollPosition)
            }

            items(chatHistory){message ->
                val messageUser : Arrangement.Horizontal = if (message.userId == 0) Arrangement.End else Arrangement.Start
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp),
                    horizontalArrangement = messageUser
                ){
                    MessageCard(message)
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .navigationBarsPadding(),
            verticalAlignment = Alignment.CenterVertically){
            TextField(
                placeholder = { Text("Message") },
                value = messageInput,
                onValueChange = { messageInput = it },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send),
                modifier = modifier.weight(1f)
            )
            IconButton(onClick = {sendMessage(messageInput)},
                modifier = Modifier.size(40.dp)){
                Image(
                    painter = painterResource(R.drawable.baseline_send_24),
                    contentDescription = stringResource(R.string.send_desc)
                )
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
                .sizeIn(150.dp, 50.dp, 200.dp)
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
    TI_MessengerTheme() {
        ChatScreen(modifier = Modifier.statusBarsPadding(), chatHistory = listOf(
            Message("Test1","03:10",1),
            Message("LOOOOOOOOOONG MESSAGE","03:10",0),
            Message("Multi\nParagraph\nMessage","03:10",0),
            Message("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque ac placerat nisi, ac venenatis quam. In in leo a neque fermentum placerat. Pellentesque tempus lectus ac eros placerat, sed scelerisque nisl placerat. Praesent quis metus ligula. Vestibulum eget dignissim eros, vel lacinia risus. Proin quis efficitur est","03:11",1),
            Message("a","03:11",0),
            Message("Test6","03:11",1),
            Message("Test7","03:11",0),
            Message("Test8","03:12",1),
            Message("Test9","03:13",0),
            Message("Test10","03:13",1),
            Message("Test11","03:13",0),
            Message("Test12","03:13",0),
            Message("Test13","03:14",1),
            Message("Test14","03:14",1),
            Message("Test15","03:14",1),
            Message("bro","03:14",1),
        ), sendMessage = {}, scrollPosition = 0, onSaveScrollPos = {})
    }
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreviewBlack() {
    TI_MessengerTheme(darkTheme = true) {
        ChatScreen(modifier = Modifier.statusBarsPadding(), chatHistory = listOf(
            Message("Test1","03:10",1),
            Message("LOOOOOOOOOONG MESSAGE","03:10",0),
            Message("Multi\nParagraph\nMessage","03:10",0),
            Message("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque ac placerat nisi, ac venenatis quam. In in leo a neque fermentum placerat. Pellentesque tempus lectus ac eros placerat, sed scelerisque nisl placerat. Praesent quis metus ligula. Vestibulum eget dignissim eros, vel lacinia risus. Proin quis efficitur est","03:11",1),
            Message("a","03:11",0),
            Message("Test6","03:11",1),
            Message("Test7","03:11",0),
            Message("Test8","03:12",1),
            Message("Test9","03:13",0),
            Message("Test10","03:13",1),
            Message("Test11","03:13",0),
            Message("Test12","03:13",0),
            Message("Test13","03:14",1),
            Message("Test14","03:14",1),
            Message("Test15","03:14",1),
            Message("bro","03:14",1),
        ), sendMessage = {}, scrollPosition = 0, onSaveScrollPos = {})
    }
}