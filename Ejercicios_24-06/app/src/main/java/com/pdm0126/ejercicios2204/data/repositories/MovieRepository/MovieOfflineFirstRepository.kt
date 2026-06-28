package com.pdm0126.ejercicios2204.data.repositories.MovieRepository

import com.pdm0126.ejercicios2204.models.Movie
import kotlinx.coroutines.flow.Flow

interface MovieOfflineFirstRepository {
  fun getMovies(): Flow<List<Movie>>
  fun getMovieById(id: Int): Flow<Movie?>
  suspend fun refresh()
  suspend fun refreshMovieById(id: Int)
}