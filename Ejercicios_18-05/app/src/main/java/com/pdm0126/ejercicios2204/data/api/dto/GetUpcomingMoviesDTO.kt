package com.pdm0126.ejercicios2204.data.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class GetUpcomingMoviesDTO(
  val dates: JsonObject,
  val page: Int,
  val results: List<MovieDTO>,
  @SerialName("total_pages") val totalPages: Int,
  @SerialName("total_results") val totalResults: Int
  ) {
}