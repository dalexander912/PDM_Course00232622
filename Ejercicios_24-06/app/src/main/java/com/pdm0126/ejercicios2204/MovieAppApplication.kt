package com.pdm0126.ejercicios2204

import android.app.Application
import com.pdm0126.ejercicios2204.data.AppProvider

class MovieAppApplication : Application() {
  val appProvider by lazy { AppProvider(this) }
}