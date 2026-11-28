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
import com.example.ti_messenger.ui.profile.ContactProfileScreen
import com.example.ti_messenger.ui.theme.TI_MessengerTheme

@Composable
fun SettingsScreen(modifier: Modifier = Modifier){
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        item { ProfilePicture() }
        //item { AccountInformation() }
        //item { ThemeSelection() }

    }
}

@Composable
fun ProfilePicture(modifier: Modifier = Modifier){
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.inversePrimary)
            .border(3.dp, color = MaterialTheme.colorScheme.primaryFixedDim)
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



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingsScreenPreview() {
    TI_MessengerTheme {
        SettingsScreen(
        )
    }
}