package com.example.ti_messenger.ui.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ti_messenger.R
import com.example.ti_messenger.ui.profile.ContactProfileScreen
import com.example.ti_messenger.ui.theme.TI_MessengerTheme

@Composable
fun SettingsScreen(modifier: Modifier = Modifier){
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        item { ProfilePicture() }
        item { AccountInformation() }
        //item { ThemeSelection() }

    }
}

@Composable
fun ProfilePicture(modifier: Modifier = Modifier){
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.tertiaryContainer)
            .border(3.dp, color = MaterialTheme.colorScheme.onTertiaryContainer)
            .padding(60.dp)) {
        Image(
            painter = painterResource(R.drawable.pfp_placeholder),
            contentDescription = stringResource(R.string.your_profile_picture),
            modifier = Modifier
                .size(100.dp)
                .padding(5.dp)
        )
        Text(
            text = stringResource(R.string.place_holder),
            modifier = Modifier,
        )
    }
}

@Composable
fun AccountInformation(modifier: Modifier = Modifier){
    Column(modifier = Modifier
        .background(color = MaterialTheme.colorScheme.tertiaryContainer)
        .fillMaxWidth()
        .border(3.dp, color = MaterialTheme.colorScheme.onTertiary)
        .padding(top = 5.dp,start = 10.dp,end = 10.dp, bottom = 10.dp)) {
        Text(
            text = stringResource(R.string.account),
            fontWeight = MaterialTheme.typography.headlineMedium.fontWeight,
            fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            color = MaterialTheme.typography.headlineMedium.color
        )
        Card(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.secondaryFixedDim)
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(4.dp),
        ){
            Text(text = "+7 777 777 77 77")
            Text(text = "Tap to change phone number",
                fontWeight = MaterialTheme.typography.labelSmall.fontWeight,
                fontStyle = MaterialTheme.typography.labelSmall.fontStyle,
                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                color = MaterialTheme.typography.labelSmall.color
            )
        }
        Card(){
            Text(text = "@Anon")
            Text(text = "Tap to change username")
        }
        Card(){
            Text(text = "Hello, I'm working on TI messenger")
            Text(text = "Tap to change bio")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingsScreenPreview() {
    TI_MessengerTheme {
        SettingsScreen(
        )
    }
}