package com.example.carcareapplication.ui.screen

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.carcareapplication.R
import com.example.carcareapplication.model.ForumPost
import com.example.carcareapplication.model.ForumPostList
import com.example.carcareapplication.model.MyCarList
import com.example.carcareapplication.model.models.ForumPosts
import com.example.carcareapplication.ui.screen.navigation.NavigationDestination
import com.example.carcareapplication.ui.screen.navigation.UserBottomBar
import com.example.carcareapplication.viewModel.AddGetForumPostsViewModel
import com.example.carcareapplication.viewModel.AppViewModelProvider
import kotlinx.coroutines.coroutineScope
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import kotlinx.coroutines.launch

object ForumDestination: NavigationDestination {
    override val route = "forum"
    override val title = "Forum"
}

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun ForumScreenWithBottomBar(
    context: Context,
    navigateToCars: () -> Unit,
    navigateToForum: () -> Unit,
    navigateToAddForum: () -> Unit,
    navigateToProfile: () -> Unit,
    //forumViewModel: AddGetForumPostsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Scaffold(
        bottomBar = { UserBottomBar(onCarsClick = navigateToCars, onForumClick = navigateToForum, onProfileClick = navigateToProfile) },
        floatingActionButton = { FloatingActionButton(onClick = { navigateToAddForum() }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add" )
        }}
    ) {
        ForumScreen(context, navigateToAddForum, /*forumViewModel*/)
    }
}

@Composable
fun ForumScreen(
    context: Context,
    navigateToAddForum: () -> Unit,
    //forumViewModel: AddGetForumPostsViewModel
    viewModel: AddGetForumPostsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.blue_50))
            //.verticalScroll(rememberScrollState()),

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp)
                .padding(bottom = 70.dp),
            //.background(color = Color.Cyan),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Forum", fontSize = 32.sp)
                IconButton(onClick = {
                    Toast.makeText(context, "Welcome to the forum! Here, you can search for help on various topics.", Toast.LENGTH_LONG).show()

                }) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = "", tint = Color(0xFF111111))
                }
            }


            Spacer(modifier = Modifier.height(24.dp))

            //LazyColumn {
            //    items(viewModel.forumPostList) {
            //        item -> Text(item.title.toString())
            //    }
            //}

            LazyColumn {
                items(viewModel.forumPostList) {
                        post -> ForumCard(forumPost = post)
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ForumCard(forumPost: ForumPosts, viewModel: AddGetForumPostsViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    var expanded by remember { mutableStateOf(false) }
    var comment by remember { mutableStateOf("")}

    val coroutineScope = rememberCoroutineScope()
    val detailState = viewModel.forumPostCommentUiState.forumPostCommentDetails

    val keyboardController = LocalSoftwareKeyboardController.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        colors = CardDefaults.cardColors(
            //containerColor = colorResource(id = R.color.clr_navigation),
            containerColor = Color(0xFFD3E3FD)

        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        border = BorderStroke(0.5.dp, colorResource(id = R.color.black))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .padding(bottom = 8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Text("Make and Model")
                Text("Temp User", fontWeight = FontWeight.Medium, fontSize = 16.sp)
                //Text(forumPost.createdAt.toString(), fontSize = 12.sp)
                Text("13.7.2024 14:45", fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(forumPost.title.toString(), fontSize = 19.sp, fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Default,
                    color = colorResource(id = R.color.blue_1000))
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(forumPost.description.toString(), fontFamily = FontFamily.Default)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "View Replies",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded },
                textAlign = TextAlign.Right,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
            )

            if (expanded) {
                Spacer(modifier = Modifier.height(10.dp))

                Row {
                    Text(text = "Add Comment:")
                    Spacer(modifier = Modifier.width(4.dp))
                    Box {
                        BasicTextField(
                            value = comment,
                            onValueChange = {
                                comment = it
                                viewModel.updateUiState(detailState.copy(description = it))
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 4.dp) // Adjust bottom padding to control the border thickness
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(0.5.dp) // Adjust height to control the border thickness
                                .background(Color.Black)
                                .align(Alignment.BottomEnd)

                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End,

                ) {
                    Button(
                        onClick = {
                          coroutineScope.launch {
                              viewModel.addForumPostComment()
                              comment = ""
                              keyboardController?.hide()
                          }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.blue_1000),
                            contentColor = colorResource(id = R.color.blue_50)
                        ),
                    ) {
                        Text(
                            text = "Comment",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = FontFamily.Monospace,
                            modifier = Modifier
                                .padding(vertical = 0.dp)
                                .padding(horizontal = 10.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                //ForumCardComment("Emir Prasovic", "Just reconnect the wires and it should work then")
                //ForumCardComment("John Doe", "Try checking your fusebox, there may be a problem there")

                for(comment in viewModel.forumPostCommentList) {
                    ForumCardComment("Temp User", comment.description.toString())
                }

        }
    }
    Spacer(modifier = Modifier.height(20.dp))
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun ForumCardComment(user: String, comment: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        colors = CardDefaults.cardColors(
            //containerColor = colorResource(id = R.color.blue_50),
            //containerColor = Color(0xFFE7F6FE)
            containerColor = Color(0xFFD3E3FD)
        ),
        shape = RoundedCornerShape(0.dp),
    ) {
        Column(

        ) {
            Text(
                text = user,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = comment,
                fontSize = 15.sp,
                //modifier = Modifier.padding(start = 4.dp)
                fontFamily = FontFamily.Default
            )
        }
    }

    Spacer(modifier = Modifier.padding(10.dp))
}

@Composable
fun ForumScreenPreview() {
    //ForumCard(forumPost = ForumPostList.forumPosts[1])
    //ForumScreen()
}