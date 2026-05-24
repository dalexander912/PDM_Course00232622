package com.pdm0126.ejercicios2204.data.repositories.MovieRepository

import com.pdm0126.ejercicios2204.models.Movie

interface MovieRepository {
  suspend fun getMovies(): List<Movie>
  suspend fun getMovieById(id: Int): Movie?
}