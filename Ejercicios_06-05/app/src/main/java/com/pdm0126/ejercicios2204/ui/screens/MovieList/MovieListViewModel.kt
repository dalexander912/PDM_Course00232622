package com.pdm0126.ejercicios2204.ui.screens.MovieList

import androidx.lifecycle.ViewModel
import com.pdm0126.ejercicios2204.data.dummyMovies
import com.pdm0126.ejercicios2204.models.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MovieListViewModel: ViewModel() {
  private val _movies = MutableStateFlow<List<Movie>>(emptyList())
  val movies = _movies.asStateFlow()

  private val _loading = MutableStateFlow(false)
  val loading = _loading.asStateFlow()

  init {
    loadMovies()
  }

  fun loadMovies() {
    _loading.value = true
    _movies.value = dummyMovies
    _loading.value = false
  }
}