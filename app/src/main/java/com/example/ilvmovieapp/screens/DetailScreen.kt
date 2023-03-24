/*package com.example.ilvmovieproject.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.ilvmovieapp.Screens
import com.example.ilvmovieproject.models.Movie
import com.example.ilvmovieproject.models.getMovies
import kotlin.random.Random


@Composable
fun DetailScreen(navController: NavController, movieId: String) {

    findMovie(movie = movieId)

    TopAppBar(result, navController)

    Card(modifier = Modifier
        .fillMaxWidth()) {

        MovieRow(movies = result)

        LazyColumn(modifier = Modifier
            .horizontalScroll(rememberScrollState())) {
            items(result.images) { images ->
                AsyncImage(model = result.images, contentDescription = result.title)
            }
        }
    }
}

var result: Movie

@Composable
fun findMovie(movie: String): Movie {

    val movies: List<Movie> = getMovies()

    movies.forEach { m ->
        if (movie == m.id){
            result = m
        }
    }
    return result
}


@Composable
fun TopAppBar(movie: Movie, navController: NavController){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .background(color = Color(0xFF0D5088))) {
        Column (modifier = Modifier
            .size(50.dp, 60.dp)
            //.background(color = Color.Green)
            , verticalArrangement = Arrangement.Center){
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", modifier = Modifier
                .clickable {
                    navController.navigate(Screens.Home.route)
                })
        }
        Column(modifier = Modifier
            //.background(color = Color.Red)
            .size(100.dp), verticalArrangement = Arrangement.Center){
            Text(text = movie.title, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

    }
}


@Composable
fun MovieRow(movies: Movie) {

    var icon by remember {
        mutableStateOf(Icons.Default.KeyboardArrowUp)
    }

    var visible by remember {
        mutableStateOf(false)
    }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Column(
            Modifier
                .padding(4.dp)
                .fillMaxWidth(), verticalArrangement = Arrangement.Center

        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp)), Alignment.Center

            ) {

                var randomPicture by remember {
                    mutableStateOf(Random.nextInt(movies.images.size - 1))
                }

                AsyncImage(model = movies.images[randomPicture], contentDescription = movies.title)
                Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "", modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(4.dp))

            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .clickable {
                        if (!visible) {
                            visible = true
                            icon = Icons.Default.KeyboardArrowDown
                        } else {
                            visible = false
                            icon = Icons.Default.KeyboardArrowUp
                        }
                    }
                    .size(40.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = movies.title, style = MaterialTheme.typography.h6, fontSize = 20.sp, fontWeight = FontWeight.Normal)

                Icon(imageVector = icon, contentDescription = "", modifier = Modifier
                    .animateContentSize()
                    .clickable {
                        if (icon == Icons.Default.KeyboardArrowUp) {
                            icon = Icons.Default.KeyboardArrowDown
                            visible = true
                        } else {
                            icon = Icons.Default.KeyboardArrowUp
                            visible = false
                        }
                    })
            }

            AnimatedVisibility(visible = visible) {

                Row(modifier = Modifier) {
                    Column(modifier = Modifier
                        .fillMaxWidth()) {
                        Row(){
                            Text(text = "Genre: ", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text(text = movies.genre, fontSize = 16.sp)
                        }
                        Row() {
                            Text(text = "Released: ", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text(text = movies.year, fontSize = 16.sp)
                        }
                        Row() {
                            Text(text = "Director: ", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text(text = movies.director, fontSize = 16.sp)
                        }
                        Row() {
                            Text(text = "Actors: ", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text(text = movies.actors, fontSize = 16.sp)
                        }

                        Row() {
                            Text(text = "Rating: ", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text(text = movies.rating, fontSize = 16.sp)
                        }
                        Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier
                            .padding(10.dp))
                        Row() {
                            Text(text = "Plot: ", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text(text = movies.plot, fontSize = 16.sp)
                        }
                    }
                }
            }
        }
    }
}
*/

package com.example.ilvmovieproject.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.ilvmovieapp.Screens
import com.example.ilvmovieproject.models.Movie
import com.example.ilvmovieproject.models.getMovies
import kotlin.random.Random

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

        Column {
            TopAppBar(navController,mo)
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFABC3D6))
                .size(15.dp))
            MovieRow(movies = mo)
        }

        Column() {
            LazyRow(){
/*
                mo.images.forEach{ img ->
                    Column(modifier = Modifier
                        .background(color = Color.Red)) {
                    }
                }*/
            }
        }
    }
}


@Composable
fun TopAppBar(navController: NavController, movie: Movie){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .background(color = Color(0xFF0D5088))) {
        Column (modifier = Modifier
            .size(50.dp, 60.dp)
            , verticalArrangement = Arrangement.Center){
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", modifier = Modifier
                .padding(10.dp)
                .size(30.dp)
                .clickable {
                    navController.navigate(Screens.Home.route)
                })
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .size(100.dp), verticalArrangement = Arrangement.Center){
            Text(text = movie.title, fontSize = 21.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
    }
}

@Composable
fun showImages(movie: Movie){

    AsyncImage(model = movie.images, contentDescription = movie.title, modifier = Modifier)

}

@Composable
fun MovieRow(movies: Movie) {

    var icon by remember {
        mutableStateOf(Icons.Default.KeyboardArrowUp)
    }

    var visible by remember {
        mutableStateOf(false)
    }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Column(
            Modifier
                .padding(4.dp)
                .fillMaxWidth(), verticalArrangement = Arrangement.Center

        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp)), Alignment.Center

            ) {

                var randomPicture by remember {
                    mutableStateOf(Random.nextInt(movies.images.size - 1))
                }

                AsyncImage(model = movies.images[randomPicture], contentDescription = movies.title)
                Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "", modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(4.dp))

            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .clickable {
                        if (!visible) {
                            visible = true
                            icon = Icons.Default.KeyboardArrowDown
                        } else {
                            visible = false
                            icon = Icons.Default.KeyboardArrowUp
                        }
                    }
                    .size(40.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = movies.title, style = MaterialTheme.typography.h6, fontSize = 20.sp, fontWeight = FontWeight.Normal)

                Icon(imageVector = icon, contentDescription = "", modifier = Modifier
                    .animateContentSize()
                    .clickable {
                        if (icon == Icons.Default.KeyboardArrowUp) {
                            icon = Icons.Default.KeyboardArrowDown
                            visible = true
                        } else {
                            icon = Icons.Default.KeyboardArrowUp
                            visible = false
                        }
                    })
            }

            AnimatedVisibility(visible = visible) {

                Row(modifier = Modifier) {
                    Column(modifier = Modifier
                        .fillMaxWidth()) {
                        Row(){
                            Text(text = "Genre: ", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text(text = movies.genre, fontSize = 16.sp)
                        }
                        Row() {
                            Text(text = "Released: ", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text(text = movies.year, fontSize = 16.sp)
                        }
                        Row() {
                            Text(text = "Director: ", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text(text = movies.director, fontSize = 16.sp)
                        }
                        Row() {
                            Text(text = "Actors: ", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text(text = movies.actors, fontSize = 16.sp)
                        }

                        Row() {
                            Text(text = "Rating: ", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text(text = movies.rating, fontSize = 16.sp)
                        }
                        Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier
                            .padding(10.dp))
                        Row() {
                            Text(text = "Plot: ", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text(text = movies.plot, fontSize = 16.sp)
                        }
                    }
                }
            }
        }
    }
}
