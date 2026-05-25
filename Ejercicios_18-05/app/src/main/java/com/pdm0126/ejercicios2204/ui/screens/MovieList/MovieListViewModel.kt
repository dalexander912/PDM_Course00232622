package com.pdm0126.ejercicios2204.ui.screens.MovieList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm0126.ejercicios2204.data.repositories.MovieRepository.MovieApiRepository
import com.pdm0126.ejercicios2204.data.repositories.MovieRepository.MovieRepository
import com.pdm0126.ejercicios2204.models.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieListViewModel: ViewModel() {
  private val movieRepository: MovieRepository = MovieApiRepository()

  private val _movies = MutableStateFlow<List<Movie>>(emptyList())
  val movies = _movies.asStateFlow()
  private val _upcomingMovies = MutableStateFlow<List<Movie>>(emptyList())
  val upcomingMovies = _upcomingMovies.asStateFlow()

  private val _loading = MutableStateFlow(false)
  val loading = _loading.asStateFlow()

  init {
    loadMovies()
  }

  fun loadMovies() {
    viewModelScope.launch {
      _loading.value = true
      _movies.value = movieRepository.getMovies()
      _upcomingMovies.value = movieRepository.getUpcomingMovies()
      _loading.value = false
    }
  }
}