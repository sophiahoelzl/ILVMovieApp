package com.example.ilvmovieapp.repository

import com.example.ilvmovieapp.data.MovieDao
import com.example.ilvmovieproject.models.Movie

class MovieRepository(private val movieDao: MovieDao) {

    suspend fun add(movie: Movie) = movieDao.add(movie)

    suspend fun delete(movie: Movie) = movieDao.delete(movie)

    suspend fun update(movie: Movie) = movieDao.update(movie)

    fun getAllMovies() = movieDao.readAll()

    fun readAllFavorite() = movieDao.readAllFavorite()

    fun getMovie(movie: Int) = movieDao.getMovieById(movie)
}