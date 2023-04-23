package com.example.ilvmovieapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ilvmovieapp.repository.MovieRepository
import com.example.ilvmovieproject.models.Movie
import com.example.ilvmovieproject.models.getMovies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val movieRepository: MovieRepository): ViewModel() {

    private val _movieList = MutableStateFlow(listOf<Movie>())
    val movieListState: StateFlow<List<Movie>> = _movieList.asStateFlow()

    init {
        viewModelScope.launch {
            movieRepository.getAllMovies().collect { movieList ->
                if (movieList.isNotEmpty()) {
                    _movieList.value = movieList
                }
            }
        }
    }

    suspend fun updateFavoriteMovies(movie: Movie) {
        movie.isFavorite = !movie.isFavorite
        movieRepository.update(movie)
    }
}