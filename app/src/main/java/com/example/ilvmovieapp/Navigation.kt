package com.example.ilvmovieapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ilvmovieapp.ViewModel.MovieViewModel
import com.example.ilvmovieapp.screens.AddMovie
import com.example.ilvmovieapp.screens.Favorites
import com.example.ilvmovieapp.screens.Screens
import com.example.ilvmovieproject.screens.DetailScreen
import com.example.ilvmovieproject.screens.HomeScreen

@Composable
fun Navigation(viewModel: MovieViewModel){

    var navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.Home.route){
        composable(Screens.Home.route) { HomeScreen(navController = navController, viewModel= viewModel)}
        composable(Screens.Favorite.route){ Favorites(navController = navController, viewModel = viewModel)}
        composable(Screens.AddMovie.route){ AddMovie(navController = navController, moviesViewModel = viewModel) }
        composable(Screens.Detail.route, arguments = listOf(navArgument("movieID"){
            type = NavType.StringType
        })) {
                backStackEntry ->
            val id = backStackEntry.arguments?.getString("movieID") ?: ""
            DetailScreen(navController = navController, movieID = id, viewModel = viewModel)}
    }
}