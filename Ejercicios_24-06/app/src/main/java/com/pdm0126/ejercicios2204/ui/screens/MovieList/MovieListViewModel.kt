package com.pdm0126.ejercicios2204.ui.screens.MovieList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm0126.ejercicios2204.data.repositories.MovieRepository.MovieOfflineFirstRepository
import com.pdm0126.ejercicios2204.models.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pdm0126.ejercicios2204.MovieAppApplication

class MovieListViewModel(
  private val repository: MovieOfflineFirstRepository
): ViewModel() {

  val movies: StateFlow<List<Movie>> = repository.getMovies()
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

  private val _isRefreshing = MutableStateFlow(false)
  val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

  private val _error = MutableStateFlow<String?>(null)
  val error: StateFlow<String?> = _error.asStateFlow()

  init { refresh() }

  fun refresh() {
    viewModelScope.launch {
      _error.value = null
      _isRefreshing.value = true
      try {
        repository.refresh()
      } catch (_: Exception) {
        // Solo mostramos error si además no hay nada en Room
        if (movies.value.isEmpty()) {
          _error.value = "Sin conexión y sin datos en caché"
        }
      }
      _isRefreshing.value = false
    }
  }

  companion object {
    val Factory = viewModelFactory {
      initializer {
        val app = this[APPLICATION_KEY] as MovieAppApplication
        MovieListViewModel(app.appProvider.provideMovieOfflineFirstRepository())
      }
    }
  }
}