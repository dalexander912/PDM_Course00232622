package com.pdm0126.ejercicios2204

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes : NavKey {
  @Serializable
  data object Home : Routes()

  @Serializable
  data class MovieDetail(val movieId: Int) : Routes()
}