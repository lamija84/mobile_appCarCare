package com.example.carcareapplication.ui.screen.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.carcareapplication.R
import com.example.carcareapplication.viewModel.AppViewModelProvider
import com.example.carcareapplication.viewModel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserBottomBar(
    onCarsClick: () -> Unit = {},
    onForumClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
) {
    BottomAppBar(
        containerColor = Color(0xFFD3E3FD),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onCarsClick) {
                Icon(painterResource(id = R.drawable.baseline_directions_car_24), contentDescription = "")
            }
            IconButton(onClick = onForumClick) {
                Icon(painterResource(id = R.drawable.baseline_forum_24), contentDescription = "")
            }
            IconButton(onClick = onProfileClick) {
                Icon(painterResource(id = R.drawable.baseline_account_circle_24), contentDescription = "")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StudentBottomBarPreview() {
    UserBottomBar(
        onCarsClick = {},
        onForumClick = {},
        onProfileClick = {}
    )
}