package com.example.ilvmovieapp.ViewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.ilvmovieapp.models.Genre
import com.example.ilvmovieapp.models.ListItemSelectable
import com.example.ilvmovieproject.models.Movie
import com.example.ilvmovieproject.models.getMovies

class MovieViewModel: ViewModel(){

    private val _movieList = getMovies().toMutableStateList()
    val movieList: List<Movie>
        get() = _movieList

    private val _favoriteMovies = mutableListOf<Movie>().toMutableStateList()
    val favoriteMovies: List<Movie>
        get() = _favoriteMovies


    fun onHeartClicked(movie: Movie) {
        val idx = movieList.indexOfFirst { it.id == movie.id }
        val m = movieList[idx]

        m.isFavorite = !m.isFavorite
        _movieList.removeAt(idx)
        _movieList.add(idx, m)

        if (m.isFavorite) _favoriteMovies.add(m)
        else _favoriteMovies.removeAt(favoriteMovies.indexOfFirst { it.id == movie.id })
    }

    fun getMovie(id: String): Movie {
        val idx = movieList.indexOfFirst { it.id == id }
        val m = movieList[idx]

        return m;
    }


    fun addMovie(
        title: String,
        year: String,
        genres: List<Genre>,
        director: String,
        actors: String,
        plot: String,
        rating: String
    ) {
        val newMovie = Movie(
            id = "$title + $year + $genres",
            title = title,
            year = year,
            genre = genres,
            director = director,
            actors = actors,
            plot = plot,
            rating = rating.toFloat(),
            images = listOf("https://i0.wp.com/writesomething.org.au/wp-content/uploads/2017/01/no.jpg?fit=1024%2C768&ssl=1")
        )
        _movieList.add(newMovie)

    }

    private var addMovie: Movie = Movie("", "", "", listOf(), "", "", "", listOf(), 0.0f)

    var title = mutableStateOf(addMovie.title)
    var titleError: MutableState<Boolean> = mutableStateOf(false)

    val year = mutableStateOf(addMovie.year)
    var yearError: MutableState<Boolean> = mutableStateOf(false)

    var director = mutableStateOf(addMovie.director)
    var directorError: MutableState<Boolean> = mutableStateOf(false)

    var actors = mutableStateOf(addMovie.actors)
    var actorsError: MutableState<Boolean> = mutableStateOf(false)

    var plot = mutableStateOf(addMovie.plot)
    var plotError: MutableState<Boolean> = mutableStateOf(false)

    var rating = mutableStateOf(addMovie.rating.toString().replace("0.0", ""))
    var ratingError: MutableState<Boolean> = mutableStateOf(false)

    var addButtonEnabled: MutableState<Boolean> = mutableStateOf(true)

    var genreItems = mutableStateOf(
        Genre.values().map { genre ->
            ListItemSelectable(
                title = genre.toString(),
                isSelected = false
            )
        }
    )
    var genreError: MutableState<Boolean> = mutableStateOf(false)


    private fun enableAddButton() {
        addButtonEnabled.value =
            (titleError.value.not()
                    && yearError.value.not()
                    && directorError.value.not()
                    && actorsError.value.not()
                    && plotError.value.not()
                    && ratingError.value.not()
                    && genreError.value.not()
                    )
    }

    fun validation() {
        validateTitle()
        validateYear()
        validateDirector()
        validateActors()
        validatePlot()
        validateGenres()
        validateRating()
    }

    fun validateTitle() {
        titleError.value = title.value.isEmpty()
        enableAddButton()
    }

    fun validateYear() {
        yearError.value = year.value.isEmpty()
        enableAddButton()
    }

    fun validateDirector() {
        directorError.value = director.value.isEmpty()
        enableAddButton()
    }

    fun validateActors() {
        actorsError.value = actors.value.isEmpty()
        enableAddButton()
    }

    fun validatePlot() {
        plotError.value = plot.value.isEmpty()
        enableAddButton()
    }

    fun validateRating() {
        try {
            rating.value.toFloat()
            ratingError.value = false
        } catch (e: java.lang.Exception) {
            ratingError.value = true
        } finally {
            enableAddButton()
        }
    }

    fun validateGenres() {
        genreError.value = true
        genreItems.value.forEach genres@{
            if (it.isSelected) {
                genreError.value = false
                return@genres
            }
        }
        enableAddButton()
    }

}