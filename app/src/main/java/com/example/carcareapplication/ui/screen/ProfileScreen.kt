package com.example.carcareapplication.ui.screen

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.carcareapplication.R
import com.example.carcareapplication.ui.screen.navigation.NavigationDestination
import com.example.carcareapplication.ui.screen.navigation.UserBottomBar
import com.example.carcareapplication.viewModel.AppViewModelProvider
import com.example.carcareapplication.viewModel.UserViewModel

// import kotlin.coroutines.jvm.internal.CompletedContinuation.context

object ProfileDestination: NavigationDestination {
    override val route = "profile"
    override val title = "Profile"
    const val userIdArg = "userID"
    val routeWithArgs = "$route/{$userIdArg}"
}

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun ProfileScreenWithBottomBar(
    navigateToCars: () -> Unit,
    navigateToForum: () -> Unit,
    navigateToProfilePage: () -> Unit,
    navigateToLoginPage: () -> Unit
) {
    Scaffold(
        bottomBar = { UserBottomBar(onCarsClick = navigateToCars, onForumClick = navigateToForum, /*onProfileClick = navigateToProfilePage */ )}
    ) {
        ProfileScreen(navigateToLoginPage = navigateToLoginPage)
    }
}

@Composable
fun ProfileScreen(
    viewModel: UserViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToLoginPage: () -> Unit
) {

    var firstName = "Emir"
    var lastName = "Prasovic"
    var email = "emirprasovic@stu.ibu.edu.ba"
    var phoneNumber = 38762554235
    val context = LocalContext.current

    var uiState = viewModel.usersUiState
    var detailsState = uiState.usersDetails

    Box(
        modifier = Modifier
            .fillMaxSize()
            //.background(color = colorResource(id = R.color.white))
            .background(colorResource(id = R.color.blue_50))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentWidth()
                .padding(vertical = 20.dp, horizontal = 20.dp),
            //.background(color = Color.Cyan), // Added padding to the top and bottom
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Logout", color = colorResource(id = R.color.clr_error), fontFamily = FontFamily.Monospace, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                    IconButton(onClick = {
                            navigateToLoginPage()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_logout_24), contentDescription = "Log out",
                            tint = colorResource(id = R.color.clr_error),
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(140.dp))
            
            ProfileImage()

            Spacer(modifier = Modifier.height(50.dp))
            
            //Text(text = "Id = ${detailsState.id}")

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "First name")
                //Spacer(modifier = Modifier.width(20.dp))
                Text(text = uiState.usersDetails.firstName)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Last name")
                //Spacer(modifier = Modifier.width(20.dp))
                Text(text = detailsState.lastName)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Email")
                //Spacer(modifier = Modifier.width(20.dp))
                Text(text = detailsState.email)
            }

            Spacer(modifier = Modifier.height(50.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val i = Intent(Intent.ACTION_SEND)
                        val emailAddress = arrayOf(detailsState.email)
                        i.putExtra(Intent.EXTRA_EMAIL, emailAddress)
                        i.setType("message/rfc822")
                        try {
                            context.startActivity(
                                Intent.createChooser(
                                    i,
                                    "Choose an Email client : "
                                )
                            )
                        } catch (s: SecurityException) {
                            Toast
                                .makeText(context, "An error occurred", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
            ) {
                Icon(Icons.Default.MailOutline, contentDescription = null, modifier = Modifier.size(40.dp), tint = Color(0xFF333333))
                Spacer(modifier = Modifier.size(width = 10.dp, height = 0.dp))
                Text(text = detailsState.email, fontSize = 18.sp, fontWeight = FontWeight.Light)
            }


            Spacer(modifier = Modifier.height(40.dp))

            /*
            Column( horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val number = Uri.parse("tel:$phoneNumber")
                        val i = Intent(Intent.ACTION_DIAL, number)
                        try {
                            context.startActivity(i)
                        } catch (s: SecurityException) {
                            Toast
                                .makeText(context, "An error occurred", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
            ) {
                Icon(Icons.Default.Phone, contentDescription = null, modifier = Modifier.size(40.dp))
                Spacer(modifier = Modifier.size(width = 20.dp, height = 0.dp))
                Text(text = phoneNumber.toString())
            }
            */

        }
    }

}

@Composable
fun ProfileImage(){
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val bitmap =  remember { mutableStateOf<Bitmap?>(null)}

    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(contract =
    ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri = uri
    }

    imageUri?.let {
        if (Build.VERSION.SDK_INT < 28) {
            bitmap.value = MediaStore.Images
                .Media.getBitmap(context.contentResolver,it)
        } else {
            val source = ImageDecoder
                .createSource(context.contentResolver,it)
            bitmap.value = ImageDecoder.decodeBitmap(source)
        }
    }

    if(bitmap.value != null){
        bitmap.value?.let{
                btm ->
            Image(bitmap = btm.asImageBitmap(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
        }
    } else{
        Image(
            painterResource(id = R.drawable.baseline_account_circle_24),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .clickable { launcher.launch("image/*") }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ProfileScreenPreview() {
    //ProfileScreen()
}