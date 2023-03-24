package com.example.ilvmovieapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ilvmovieproject.screens.DetailScreen
import com.example.ilvmovieproject.screens.HomeScreen

@Composable
fun Navigation(navController: NavHostController){
    
    NavHost(navController = navController, startDestination = Screens.Home.route){
        composable("HomeScreen") { HomeScreen(navController = navController)}
        composable("DetailScreen/{movieID}", arguments = listOf(navArgument("movieId") {
            type = NavType.StringType
        })
    }
}