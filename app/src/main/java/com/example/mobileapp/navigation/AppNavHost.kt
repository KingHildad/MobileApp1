package com.example.mobileapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobileapp.ui.theme.screens.SplashScreen
import com.example.mobileapp.ui.theme.screens.login.LoginScreen
import com.example.mobileapp.ui.theme.screens.logout.LogoutScreen
import com.example.mobileapp.ui.theme.screens.register.Greeting
import com.example.mobileapp.ui.theme.screens.start.StartScreen
import com.example.mobileapp.ui.theme.screens.blog.Student
import com.example.mobileapp.ui.theme.screens.blog.UpdateStudent
import com.example.mobileapp.ui.theme.screens.blog.ViewStudentsScreen

@Composable
fun AppNavHost(
    navController: NavHostController= rememberNavController(),
    startDestination: String= ROUTE_START
){
    NavHost(navController = navController, startDestination=startDestination){
        composable(ROUTE_REGISTER){ Greeting(navController)}
        composable(ROUTE_LOGIN){ LoginScreen(navController)}
        composable(ROUTE_LOGOUT){ LogoutScreen(navController)}
        composable(ROUTE_SPLASH){ SplashScreen(navController) }
        composable(ROUTE_ADD_STUDENT){ Student(navController)}
        composable(ROUTE_START){ StartScreen(navController)}
        composable(ROUTE_VIEW_STUDENT){ViewStudentsScreen(navController)}
        composable(ROUTE_UPDATE_STUDENT+"/{id}"){ passedData -> UpdateStudent(navController,passedData.arguments?.getString("id")!! ) }
    }


}