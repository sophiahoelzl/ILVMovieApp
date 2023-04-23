package com.example.ilvmovieapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.ilvmovieapp.MovieRow
import com.example.ilvmovieapp.ViewModel.FavoriteScreenViewModel
import kotlinx.coroutines.launch


@Composable
fun Favorites(navController: NavController, favViewModel: FavoriteScreenViewModel){

    val favoriteMoviesState by favViewModel.favoriteMoviesState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Card(modifier = Modifier
        .fillMaxWidth()) {

        Column(modifier = Modifier.background(color = Color(0xFFABC3D6))) {

            com.example.ilvmovieapp.TopAppBar(navController = navController, title = "FAVORITES")

            LazyColumn {
                items(favoriteMoviesState) { movie ->
                    MovieRow(
                        movies = movie,
                        onHeartClicked = {movie ->
                            coroutineScope.launch {
                                favViewModel.updateFavoriteMovies(movie)
                            }},
                        onItemClicked = {movie -> navController.navigate(route = Screens.Detail.passID(movie))})
                }
            }
        }
    }
}

