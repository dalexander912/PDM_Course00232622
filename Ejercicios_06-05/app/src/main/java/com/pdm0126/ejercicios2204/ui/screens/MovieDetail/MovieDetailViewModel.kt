package com.pdm0126.ejercicios2204.ui.screens.MovieDetail

import androidx.lifecycle.ViewModel
import com.pdm0126.ejercicios2204.data.dummyMovies
import com.pdm0126.ejercicios2204.models.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MovieDetailViewModel: ViewModel() {
  private val _movie = MutableStateFlow<Movie?>(null)
  val movie = _movie.asStateFlow()

  private val _loading = MutableStateFlow(false)
  val loading = _loading.asStateFlow()

  fun loadMovieById(id: Int) {
    _loading.value = true
    _movie.value = dummyMovies.find { it.id == id }
    _loading.value = false
  }
}