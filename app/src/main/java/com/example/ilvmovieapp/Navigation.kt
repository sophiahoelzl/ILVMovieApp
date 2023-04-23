package com.example.ilvmovieapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ilvmovieapp.ViewModel.AddScreenViewModel
import com.example.ilvmovieapp.ViewModel.FavoriteScreenViewModel
import com.example.ilvmovieapp.ViewModel.HomeScreenViewModel
import com.example.ilvmovieapp.screens.Favorites
import com.example.ilvmovieapp.screens.Screens
import com.example.ilvmovieproject.screens.AddMovieScreen
import com.example.ilvmovieproject.screens.DetailScreen
import com.example.ilvmovieproject.screens.HomeScreen

@Composable
fun Navigation(HomeViewModel: HomeScreenViewModel, FavoriteViewModel: FavoriteScreenViewModel, AddMovieViewModel: AddScreenViewModel){

    var navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.Home.route){
        composable(Screens.Home.route) {
            HomeScreen(navController = navController, homeviewModel = HomeViewModel)}

        composable(Screens.Favorite.route){
            Favorites(navController = navController, favViewModel = FavoriteViewModel)}

        composable(Screens.AddMovie.route){
            AddMovieScreen(navController = navController, addScreenViewModel = AddMovieViewModel)
        }
        composable(Screens.Detail.route, arguments = listOf(navArgument("movieID"){
            type = NavType.StringType
        })) {
                backStackEntry ->
            val id = backStackEntry.arguments?.getString("movieID") ?: ""
            DetailScreen(navController = navController, movieID = id, detailViewModel = HomeViewModel)}
    }
}