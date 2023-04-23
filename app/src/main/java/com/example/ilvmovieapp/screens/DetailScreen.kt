package com.example.ilvmovieproject.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ilvmovieapp.MovieRow
import com.example.ilvmovieapp.ViewModel.HomeScreenViewModel
import com.example.ilvmovieproject.models.Movie
import kotlinx.coroutines.launch


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DetailScreen(navController: NavController, movieID: String, detailViewModel: HomeScreenViewModel) {

    val coroutineScope = rememberCoroutineScope()
    movieID?.let {
    Card(modifier = Modifier
        .fillMaxWidth()) {

            Column () {
                val mo = detailViewModel.movieListState.value.filter { it.id == movieID }[0]
                com.example.ilvmovieapp.TopAppBar(navController = navController, title = mo.title)
                MovieRow(movies = mo, onHeartClicked = {movieId ->
                    coroutineScope.launch {
                        detailViewModel.updateFavoriteMovies(movieId)
                    }})

                Text(text = "MOVIE IMAGES", fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier
                    .fillMaxWidth()
                    .size(40.dp))

                HorizontalScrollableImageView(movie = mo)
            }
        }
    }
}

@Composable
fun HorizontalScrollableImageView(movie: Movie) {
    LazyRow {
        items(movie.images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp),
                elevation = 4.dp
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(image)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Movie image",
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}


