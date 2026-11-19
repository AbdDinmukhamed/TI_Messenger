package com.example.ti_messenger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ti_messenger.data.Contact
import com.example.ti_messenger.ui.theme.TI_MessengerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TI_MessengerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ContactCard(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ContactCard(
    contact: Contact,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier.padding(top = 4.dp, bottom = 12.dp),
        shape = RoundedCornerShape(4.dp)
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
                    Text(text = stringResource(contact.nickname))
                    Text(text = stringResource(contact.lastTime))
                }
                Text(text = stringResource(contact.lastMessage), modifier = Modifier.padding(bottom = 4.dp))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    TI_MessengerTheme {
        ContactCard(modifier = Modifier.statusBarsPadding())
    }
}