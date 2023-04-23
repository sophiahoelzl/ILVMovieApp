package com.example.ilvmovieapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ilvmovieapp.repository.MovieRepository
import com.example.ilvmovieproject.models.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoriteScreenViewModel(private val movieRepository: MovieRepository): ViewModel() {

    private val _favoriteMovies = MutableStateFlow(listOf<Movie>())
    val favoriteMoviesState: StateFlow<List<Movie>> = _favoriteMovies.asStateFlow()

    init {
        viewModelScope.launch {
            movieRepository.readAllFavorite().collect() { favoriteList ->
                _favoriteMovies.value = favoriteList
            }
        }
    }

    suspend fun updateFavoriteMovies(movie: Movie) {
        movie.isFavorite = !movie.isFavorite
        movieRepository.update(movie)
    }

}