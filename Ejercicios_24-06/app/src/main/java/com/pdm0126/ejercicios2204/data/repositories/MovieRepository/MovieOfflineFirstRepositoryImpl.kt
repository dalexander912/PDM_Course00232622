package com.pdm0126.ejercicios2204.data.repositories.MovieRepository

import com.pdm0126.ejercicios2204.data.api.KtorClient
import com.pdm0126.ejercicios2204.data.api.dto.GetMoviesResponseDTO
import com.pdm0126.ejercicios2204.data.api.dto.MovieDTO
import com.pdm0126.ejercicios2204.data.api.dto.toEntity
import com.pdm0126.ejercicios2204.data.database.dao.MovieDao
import com.pdm0126.ejercicios2204.data.database.entities.toModel
import com.pdm0126.ejercicios2204.models.Movie
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.collections.map

class MovieOfflineFirstRepositoryImpl(
  private val dao: MovieDao,
) : MovieOfflineFirstRepository {

  // Room manda: la UI siempre observa esto
  override fun getMovies(): Flow<List<Movie>> =
    dao.getAll().map { list -> list.map { it.toModel() } }

  // El detalle también es reactivo y lee de Room
  override fun getMovieById(id: Int): Flow<Movie?> =
    dao.observeById(id).map { it?.toModel() }

  override suspend fun refresh() {
    val popularMovies = fetchPopular()
    dao.upsertAll(popularMovies.map { it.toEntity() })
  }

  override suspend fun refreshMovieById(id: Int) {
    val movie = fetchById(id)
    dao.upsert(movie.toEntity())
  }


  private suspend fun fetchPopular(): List<MovieDTO> =
    KtorClient.client.get("movie/popular") {
      parameter("language", "es-ES")
      parameter("page", 1)
    }.body<GetMoviesResponseDTO>().results

  private suspend fun fetchById(id: Int): MovieDTO =
    KtorClient.client.get("movie/$id") {
      parameter("language", "es-ES")
    }.body()
}