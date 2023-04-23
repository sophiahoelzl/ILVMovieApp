package com.example.ilvmovieapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ilvmovieproject.R
import com.example.ilvmovieproject.models.Movie


@Composable
fun MovieRow(
    movies: Movie,
    onItemClicked:(String) -> Unit = {},
    onHeartClicked:(Movie) -> Unit = {}){

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
            .clickable {
                onItemClicked(movies.id)
            }
    ) {
        Column(
            Modifier
                .padding(4.dp)
                .fillMaxWidth(), verticalArrangement = Arrangement.Center

        ) {
            Box(
                Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))

            ) {


                if (movies.images.isNotEmpty()){
                    AsyncImage(model = movies.images[0], contentDescription = movies.title, modifier = Modifier)
                }
                FavIcon(movies, onFavClicked = onHeartClicked, liked = movies.isFavorite)

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
                            Text(text = movies.genre.toString().replace("[","").replace("]", ""), fontSize = 16.sp)
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
                            Text(text = movies.rating.toString(), fontSize = 16.sp)
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

@Composable
fun FavIcon(movie: Movie, liked: Boolean, onFavClicked:(Movie) -> Unit){

    Box(modifier = Modifier
        .width(400.dp)
        .height(50.dp),
        contentAlignment = Alignment.CenterEnd)
    {
        IconButton(modifier = Modifier
            .size(70.dp),
            onClick = {
                onFavClicked(movie)
            },
        ) {

            Icon(modifier = Modifier
                .size(30.dp),
                tint = Color.Red,
                imageVector =
                if (liked) Icons.Default.Favorite
                else Icons.Default.FavoriteBorder,
                contentDescription = "Add to favorites",
            )
        }
    }
}

