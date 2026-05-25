package com.pdm0126.ejercicios2204.data.repositories.MovieRepository

import com.pdm0126.ejercicios2204.data.dummy.dummyMovies
import com.pdm0126.ejercicios2204.models.Movie
import kotlinx.coroutines.delay

class MovieDummyRepository: MovieRepository {
  override suspend fun getMovies(): List<Movie> {
    delay(2000)
    return dummyMovies
  }

  override suspend fun getMovieById(id: Int): Movie? {
    delay(2000)
    return dummyMovies.find { it.id == id }
  }

  override suspend fun getUpcomingMovies(): List<Movie> {
    return dummyMovies
  }
}