package com.pdm0126.repaso2

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes : NavKey {
  @Serializable
  data object Home : Routes()

  @Serializable
  data object GetScreen : Routes()

  @Serializable
  data object CreateScreen : Routes()

  @Serializable
  data object GetAllScreen : Routes()
}