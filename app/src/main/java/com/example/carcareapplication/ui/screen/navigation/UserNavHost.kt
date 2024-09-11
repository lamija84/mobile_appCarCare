package com.example.carcareapplication.ui.screen.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.carcareapplication.ui.screen.*

@Composable
fun UserNavHost(
    navController: NavHostController,
    context: Context
){
    NavHost(navController = navController, startDestination = LandingDestination.route){
        composable(route = LoginDestination.route){
            LoginScreen(
                navigateToLanding ={ navController.navigate(LandingDestination.route) },
                navigateToProfilePage = { navController.navigate("${ProfileDestination.route}/${it}") }
            )
        }
        composable(route = ProfileDestination.routeWithArgs, arguments = listOf(navArgument(ProfileDestination.userIdArg) {
            type = NavType.IntType })
        ){
            ProfileScreenWithBottomBar(
                navigateToProfilePage = { navController.navigate("${ProfileDestination.route}/1")},
                navigateToCars = { navController.navigate(CarDestination.route)},
                navigateToForum = { navController.navigate(ForumDestination.route)},
                navigateToLoginPage = { navController.navigate(LoginDestination.route)}
            )
        }
        composable(route = LandingDestination.route){
            LandingScreen(
                navigateToLogin = { navController.navigate(LoginDestination.route)},
                navigateToRegister = { navController.navigate(RegisterDestination.route)}
            )
        }
        composable(route = RegisterDestination.route){
            RegisterScreen(
                navigateToLogin = { navController.navigate(LoginDestination.route)},
                navigateToLanding = { navController.navigate(LandingDestination.route)}
            )
        }
        composable(route = ForumDestination.route){
            ForumScreenWithBottomBar(
                context = context,
                navigateToCars = { navController.navigate(CarDestination.route)},
                navigateToForum = { navController.navigate(ForumDestination.route)},
                navigateToAddForum = { navController.navigate(AddForumDestination.route)},
                navigateToProfile = { navController.navigate("${ProfileDestination.route}/1")},
            )
        }
        composable(route = CarDestination.route){
            MyCarsScreenWithBottomBar(
                navigateToCars = { navController.navigate(CarDestination.route)},
                navigateToAddCar = { navController.navigate(AddCarDestination.route)},
                navigateToUpdateCar = { navController.navigate(UpdateCarDestination.route)},
                navigateToForum = { navController.navigate(ForumDestination.route)},
                navigateToProfile = { navController.navigate("${ProfileDestination.route}/1")}
            )
        }
        composable(route = AddCarDestination.route){
            AddCarScreen(
                navigateToCars = { navController.navigate(CarDestination.route)},
            )
        }
        composable(route = AddForumDestination.route){
            AddForumPostScreen(
                navigateToForum = { navController.navigate(ForumDestination.route)},
            )
        }
        composable(route = UpdateCarDestination.route){
            UpdateCarScreen(
                navigateToCars = { navController.navigate(CarDestination.route)},
            )
        }
    }
}