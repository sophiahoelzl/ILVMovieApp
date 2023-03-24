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
import com.example.ilvmovieproject.models.Movie
import com.example.ilvmovieproject.models.getMovies


@Composable
fun Favorites(navController: NavController){

    Card(modifier = Modifier
        .fillMaxWidth()) {

        var movie: List<Movie> = getMovies()
        var fornow: List<Movie> = listOf(movie[1], movie[3], movie[5], movie[7])

        Column(modifier = Modifier.background(color = Color(0xFFABC3D6))) {

            com.example.ilvmovieapp.TopAppBar(navController = navController, title = "FAVORITES")

            LazyColumn {
                items(fornow) { movie ->
                    com.example.ilvmovieproject.screens.MovieRow(movies = movie, navController = navController)
                }
            }
        }
    }
}

