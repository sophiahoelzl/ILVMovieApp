package com.example.ilvmovieproject.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.ilvmovieapp.MovieRow
import com.example.ilvmovieproject.models.Movie
import com.example.ilvmovieproject.models.getMovies

@Composable
fun findMovie(movieID: String): Movie{

    val movies: List<Movie> = getMovies()

    movies.forEach { m ->
        if (movieID == m.id){
            return m
        }
    }
    return movies[1]
}

@Composable
fun DetailScreen(navController: NavController, movieID: String) {

    var mo: Movie = findMovie(movieID)


    Card(modifier = Modifier
        .fillMaxWidth()) {

        Column (modifier = Modifier) {
            com.example.ilvmovieapp.TopAppBar(navController = navController, title = mo.title)
            MovieRow(movies = mo)

            Text(text = "MOVIE IMAGES", fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier
                .fillMaxWidth()
                .size(40.dp))

            LazyRow {
                items(mo.images) { item ->
                    Box(modifier = Modifier
                        .padding(8.dp)){
                        AsyncImage(model = item, contentDescription = mo.title)
                    }
                }
            }
        }
    }
}



