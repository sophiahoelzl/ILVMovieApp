package com.example.ilvmovieapp.data

import androidx.room.*
import com.example.ilvmovieproject.models.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    //CRUD

    @Insert
    suspend fun add(movie: Movie)

    @Update
    suspend fun update(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Query("Select * from movie")
    fun readAll(): Flow<List<Movie>>

    @Query("Select * from movie where isFavorite = 1")
    fun readAllFavorite(): Flow<List<Movie>>

    @Query("Select * from movie where id=:movieId")
    fun getMovieById(movieId: Int): Flow<Movie?>

}