package com.example.ti_messenger.ui.contacts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ti_messenger.data.remote.RandomUser
import com.example.ti_messenger.data.remote.RandomUserPicture
import com.example.ti_messenger.data.remote.RandomUsername
import com.example.ti_messenger.data.remote.RandomUsersUiState
import com.example.ti_messenger.ui.theme.TI_MessengerTheme

@Composable
fun DiscoverPeopleSection(
    uiState: RandomUsersUiState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        Text(text = "Discover People")
        when (uiState){
            is RandomUsersUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp),
                    contentAlignment = Alignment.Center
                ){
                    LinearProgressIndicator()
                }
            }
            is RandomUsersUiState.Success -> {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)){
                    items(items = uiState.users){ user ->
                        RandomUserCard(user)
                    }
                }
            }
            is RandomUsersUiState.Empty -> {
                Text(text = "No users available.")
            }
            is RandomUsersUiState.Error -> {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ){
                    Text(text = "Failed to load users.")
                    Text(text = uiState.message)

                    Button(onClick = onRetry){
                        Text("Retry")
                    }
                }
            }
        }
    }
}


@Composable
fun RandomUserCard(
    user: RandomUser,
    modifier: Modifier = Modifier
){
    Card(modifier = modifier.height(140.dp)){
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            AsyncImage(
                model = user.picture.medium,
                contentDescription = "Profile picture",
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = "${user.name.first} ${user.name.last}"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RandomUserCardPreview(){
    val sampleUser = RandomUser(
        name = RandomUsername(
            title = "Mr",
            first = "John",
            last = "Smith"
        ),
        picture = RandomUserPicture(
            large = "https://randomuser.me/api/portraits/men/1.jpg",
            medium = "https://randomuser.me/api/portraits/med/men/1.jpg",
            thumbnail = "https://randomuser.me/api/portraits/thumb/men/1.jpg"
        )
    )
    TI_MessengerTheme{
        RandomUserCard(user = sampleUser)
    }
}