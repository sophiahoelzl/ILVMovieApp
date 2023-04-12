package com.example.ilvmovieproject.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.ilvmovieapp.FavIcon
import com.example.ilvmovieapp.ViewModel.MovieViewModel
import com.example.ilvmovieapp.screens.Screens
import com.example.ilvmovieproject.models.Movie
import com.example.ilvmovieproject.models.getMovies
import kotlin.random.Random

@Composable
fun HomeScreen(navController: NavController, viewModel: MovieViewModel ){

    Column() {
        TopAppBar(navController)
        MovieList(navController= navController, viewModel= viewModel)
    }
}



@Composable
fun TopAppBar(navController: NavController){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .background(color = Color(0xFF0D5088))) {
        Column(modifier = Modifier
            .size(100.dp), verticalArrangement = Arrangement.Center){
            Text(text = "  MOVIES", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
        Spacer(modifier = Modifier.weight(1f))
        Column (modifier = Modifier
            .size(50.dp, 60.dp)
            , verticalArrangement = Arrangement.Center){
            DropDown(navController)
        }
    }
}

@Composable
fun DropDown(navController: NavController){
    val contextForToast = LocalContext.current.applicationContext

    var expanded by remember {
        mutableStateOf(false)
    }

    Box(contentAlignment = Alignment.CenterStart){
        IconButton(onClick = {
            expanded = true
        }) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Menu", modifier = Modifier
                .size(32.dp))
        }

        DropdownMenu(modifier = Modifier
            .background(color = Color(0xFFFFFFFF)), expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(
                onClick = {
                    Toast.makeText(contextForToast, "Favorites", Toast.LENGTH_SHORT)
                        .show()
                    expanded = false
                },
            ) {
                Icon(imageVector = Icons.Filled.Favorite, contentDescription = "", tint = Color.Black)
                Spacer(modifier = Modifier
                    .size(10.dp))
                Text(text = "Favorites", modifier = Modifier
                    .clickable {
                        navController.navigate(Screens.Favorite.route)
                    })
            }

            DropdownMenuItem(
                onClick = {
                    Toast.makeText(contextForToast, "Add movie", Toast.LENGTH_SHORT)
                        .show()
                    expanded = false
                },
            ) {
                Icon(imageVector = Icons.Filled.Edit, contentDescription = "", tint = Color.Black)
                Spacer(modifier = Modifier
                    .size(10.dp))
                Text(text = "Add movie", modifier = Modifier
                    .clickable {
                        navController.navigate(Screens.AddMovie.route)
                    })
            }
        }
    }
}

@Composable
fun MovieList(navController: NavController, viewModel: MovieViewModel) {
    LazyColumn() {
        items(viewModel.movieList) { movie ->
            com.example.ilvmovieapp.MovieRow(
                movies = movie,
                onItemClicked = {movieId -> navController.navigate(route = Screens.Detail.passID(movieId))},
                onHeartClicked = {movieId -> viewModel.onHeartClicked(movieId)} )
        }
    }
}