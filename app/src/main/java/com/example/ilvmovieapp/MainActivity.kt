package com.example.ilvmovieproject

import android.database.CursorWindow
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ilvmovieapp.Navigation
import com.example.ilvmovieapp.ViewModel.*
import com.example.ilvmovieapp.data.MovieDatabase
import com.example.ilvmovieapp.repository.MovieRepository
import com.example.ilvmovieapp.ui.theme.ILVMovieAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            val field = CursorWindow::class.java.getDeclaredField("sCursorWindowSize")
            field.isAccessible = true
            field[null] = 100 * 1024 * 1024 //the 100MB is the new size
        } catch (e: Exception) {
            e.printStackTrace()
        }

        setContent {
            ILVMovieAppTheme {
                Surface(modifier = Modifier
                    .fillMaxSize(),
                    color = Color(0xFFABC3D6)
                ) {

                    Column {
                        val db = MovieDatabase.getDatabase(LocalContext.current)
                        val repository = MovieRepository(movieDao = db.movieDao())
                        val factoryHome = MovieViewModelFactory(repository = repository)
                        val factoryFav = FavoriteScreenViewModelFactory(repository = repository)
                        val factoryAdd = AddScreenViewModelFactory(repository = repository)
                        val HomeScreenViewModel: HomeScreenViewModel = viewModel(factory = factoryHome)
                        val FavoriteScreenViewModel: FavoriteScreenViewModel = viewModel(factory = factoryFav)
                        val AddMovieScreenViewModel: AddScreenViewModel = viewModel(factory = factoryAdd)

                        Navigation(HomeScreenViewModel,FavoriteScreenViewModel,AddMovieScreenViewModel)
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
        }
    }
}
