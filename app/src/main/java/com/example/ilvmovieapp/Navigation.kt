package com.example.ilvmovieapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ilvmovieapp.screens.Favorites
import com.example.ilvmovieapp.screens.Screens
import com.example.ilvmovieproject.screens.DetailScreen
import com.example.ilvmovieproject.screens.HomeScreen

@Composable
fun Navigation(navController: NavHostController){
    
    NavHost(navController = navController, startDestination = Screens.Home.route){
        composable(Screens.Home.route) { HomeScreen(navController = navController)}
        composable(Screens.Favorite.route){ Favorites(navController = navController)}
        composable(Screens.Detail.route, arguments = listOf(navArgument("movieID"){
            type = NavType.StringType
        })) {
                backStackEntry ->
            val id = backStackEntry.arguments?.getString("movieID") ?: ""
            DetailScreen(navController = navController, movieID = id)}
    }
}