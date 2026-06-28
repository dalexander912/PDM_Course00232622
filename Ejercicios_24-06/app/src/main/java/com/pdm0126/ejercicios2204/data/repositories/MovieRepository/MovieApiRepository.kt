package com.pdm0126.ejercicios2204.data.repositories.MovieRepository

import com.pdm0126.ejercicios2204.data.api.KtorClient
import com.pdm0126.ejercicios2204.data.api.dto.GetMoviesResponseDTO
import com.pdm0126.ejercicios2204.data.api.dto.GetUpcomingMoviesDTO
import com.pdm0126.ejercicios2204.data.api.dto.MovieDTO
import com.pdm0126.ejercicios2204.data.api.dto.toModel
import com.pdm0126.ejercicios2204.models.Movie
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class MovieApiRepository : MovieRepository {
  override suspend fun getMovies(): List<Movie> {
    val response: GetMoviesResponseDTO = KtorClient.client.get("movie/popular") {
      parameter("language", "es-ES")
      parameter("page", 1)
    }.body()

    return response.results.map { movieDTO -> movieDTO.toModel() }
  }

  override suspend fun getMovieById(id: Int): Movie {
    val response: MovieDTO = KtorClient.client.get("movie/$id") {
      parameter("language", "es-ES")
    }.body()

    return response.toModel()
  }

  override suspend fun getUpcomingMovies(): List<Movie> {
    val response: GetUpcomingMoviesDTO = KtorClient.client.get("movie/upcoming") {
      parameter("language", "es-ES")
      parameter("page", 1)
    }.body()

    return response.results.map { MovieDTO -> MovieDTO.toModel() }
  }
}