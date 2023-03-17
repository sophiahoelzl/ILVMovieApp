package com.example.ilvmovieproject

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
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
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ilvmovieapp.ui.theme.ILVMovieAppTheme
import com.example.ilvmovieproject.models.Movie
import com.example.ilvmovieproject.models.getMovies
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ILVMovieAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFABC3D6)
                    //color = MaterialTheme.colors.primary
                    //color = MaterialTheme.colors.background
                ) {
                    Column() {
                        TopAppBar()
                        MovieList()
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ILVMovieAppTheme {
        Column(modifier = Modifier
            .background(color = Color(0xFF8BBFE9))) {
            TopAppBar()
            MovieList()
        }
    }
}


@Composable
fun TopAppBar(){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .background(color = Color(0xFF0D5088))) {
        Column(modifier = Modifier
            //.background(color = Color.Red)
            .size(100.dp), verticalArrangement = Arrangement.Center){
            Text(text = "  MOVIES", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
        Spacer(modifier = Modifier.weight(1f))
        Column (modifier = Modifier
            .size(50.dp, 60.dp)
            //.background(color = Color.Green)
            , verticalArrangement = Arrangement.Center){
            DropDown()
        }
    }
}

@Composable
fun DropDown(){
    val items = arrayOf("Favorites")
    val disableItem = 1
    val contextForToast = LocalContext.current.applicationContext

    var expanded by remember {
        mutableStateOf(false)
    }

    Box(contentAlignment = Alignment.CenterStart){
        IconButton(onClick = {
            expanded = true
        }) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Menu")
        }

        DropdownMenu(modifier = Modifier
            .background(color = Color(0xFFFFFFFF)), expanded = expanded, onDismissRequest = { expanded = false }) {
            items.forEachIndexed { itemIndex, itemValue ->
                DropdownMenuItem(
                    onClick = {
                        Toast.makeText(contextForToast, itemValue, Toast.LENGTH_SHORT)
                            .show()
                        expanded = false
                    },
                    enabled = (itemIndex != disableItem)
                ) {
                    Text(text = itemValue)
                }
            }
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
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
                        if (!visible){
                            visible = true
                            icon = Icons.Default.KeyboardArrowDown
                        }else{
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



@Composable
fun MovieList(movies: List<Movie> = getMovies()) {
    LazyColumn() {
        items(movies) { movie ->
            MovieRow(movie)
        }
    }
}