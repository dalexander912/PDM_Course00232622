package com.pdm0126.repasop2

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes: NavKey {
  @Serializable
  data object Home: Routes()

  @Serializable
  data object GetScreen: Routes()

  @Serializable
  data object PostScreen: Routes()
}