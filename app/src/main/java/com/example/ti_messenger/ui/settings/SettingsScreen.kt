package com.example.ti_messenger.ui.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ti_messenger.R
import com.example.ti_messenger.ui.profile.ContactProfileScreen
import com.example.ti_messenger.ui.theme.TI_MessengerTheme

@Composable
fun SettingsScreen(modifier: Modifier = Modifier,
                   onThemeSwitch: () -> Unit){
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        item { ProfilePicture()
            HorizontalDivider(Modifier.fillMaxWidth(), thickness = 1.dp, color = MaterialTheme.colorScheme.primaryContainer)
        }

        item { AccountInformation()
            HorizontalDivider(Modifier.fillMaxWidth(), thickness = 1.dp, color = MaterialTheme.colorScheme.primaryContainer)
        }
        item { ThemeSelection(onThemeSwitch = onThemeSwitch)
                 HorizontalDivider(Modifier.fillMaxWidth(), thickness = 1.dp, color = MaterialTheme.colorScheme.primaryContainer)}

    }
}

@Composable
fun ProfilePicture(modifier: Modifier = Modifier){
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
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
        .background(color = MaterialTheme.colorScheme.background)
        .fillMaxWidth()
        .padding(top = 5.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)) {
        Text(
            text = stringResource(R.string.account),
            fontWeight = MaterialTheme.typography.headlineMedium.fontWeight,
            fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            color = MaterialTheme.typography.headlineMedium.color
        )
        Card(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(4.dp),
            colors = CardColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground,
                disabledContainerColor = MaterialTheme.colorScheme.errorContainer,
                disabledContentColor = MaterialTheme.colorScheme.onErrorContainer
            )
        )
        {
            Text(text = "+7 777 777 77 77")
            Text(text = "Tap to change phone number",
                fontWeight = MaterialTheme.typography.labelSmall.fontWeight,
                fontStyle = MaterialTheme.typography.labelSmall.fontStyle,
                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                color = MaterialTheme.typography.labelSmall.color
            )
        }
        HorizontalDivider(Modifier.fillMaxWidth(), thickness = 1.dp, color = MaterialTheme.colorScheme.secondaryContainer)
        Card(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(4.dp),
            colors = CardColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground,
                disabledContainerColor = MaterialTheme.colorScheme.errorContainer,
                disabledContentColor = MaterialTheme.colorScheme.onErrorContainer
            )
        ){
            Text(text = "@Anon")
            Text(text = "Tap to change username",
                fontWeight = MaterialTheme.typography.labelSmall.fontWeight,
                fontStyle = MaterialTheme.typography.labelSmall.fontStyle,
                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                color = MaterialTheme.typography.labelSmall.color)
        }
        HorizontalDivider(Modifier.fillMaxWidth(), thickness = 1.dp, color = MaterialTheme.colorScheme.secondaryContainer)

        Card(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(4.dp),
            colors = CardColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground,
                disabledContainerColor = MaterialTheme.colorScheme.errorContainer,
                disabledContentColor = MaterialTheme.colorScheme.onErrorContainer
            )
        ){
            Text(text = "Hello, I'm working on TI messenger")
            Text(text = "Tap to change bio",
                fontWeight = MaterialTheme.typography.labelSmall.fontWeight,
                fontStyle = MaterialTheme.typography.labelSmall.fontStyle,
                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                color = MaterialTheme.typography.labelSmall.color)
        }
        HorizontalDivider(Modifier.fillMaxWidth(), thickness = 1.dp, color = MaterialTheme.colorScheme.secondaryContainer)

    }
}

@Composable
fun ThemeSelection(modifier: Modifier = Modifier,
                   onThemeSwitch: () -> Unit){
    Column(modifier = Modifier
        .background(color = MaterialTheme.colorScheme.background)
        .fillMaxWidth()
        .padding(top = 5.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)) {
        Text(
            text = stringResource(R.string.theme),
            fontWeight = MaterialTheme.typography.headlineMedium.fontWeight,
            fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            color = MaterialTheme.typography.headlineMedium.color
        )
        IconButton(onClick = onThemeSwitch,
        ){
            Image(
                painter = painterResource(R.drawable.baseline_settings_24),
                contentDescription = stringResource(R.string.settings),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary)
            )
        }
    }
}
/*
@Composable
fun RadioButtonSingleSelection(modifier: Modifier = Modifier) {
    val radioOptions = listOf("Dark", "Light")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
    // Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
    Column(modifier.selectableGroup()) {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null // null recommended for accessibility with screen readers
                )
                Text(text = text)
            }
        }
    }
}
*/
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingsScreenPreview() {
    TI_MessengerTheme {
        SettingsScreen(onThemeSwitch = {}
        )
    }
}