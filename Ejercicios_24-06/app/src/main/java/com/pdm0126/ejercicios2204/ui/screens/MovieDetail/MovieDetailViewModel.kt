package com.pdm0126.ejercicios2204.ui.screens.MovieDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm0126.ejercicios2204.data.repositories.MovieRepository.MovieOfflineFirstRepository
import com.pdm0126.ejercicios2204.models.Movie
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pdm0126.ejercicios2204.MovieAppApplication

class MovieDetailViewModel(
  private val repository: MovieOfflineFirstRepository,
  private val movieId: Int
): ViewModel() {

  val movie: StateFlow<Movie?> = repository.getMovieById(movieId)
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)

  init { refresh() }

  fun refresh() {
    viewModelScope.launch {
      try {
        repository.refreshMovieById(movieId)
      } catch (e: Exception) {
        // Si falla la API nos quedamos con lo que hay en Room
        e.printStackTrace()
      }
    }
  }

  companion object {
    // Factory con parámetro: assisted injection sin librerías externas
    fun Factory(movieId: Int) = viewModelFactory {
      initializer {
        val app = this[APPLICATION_KEY] as MovieAppApplication
        MovieDetailViewModel(
          repository = app.appProvider.provideMovieOfflineFirstRepository(),
          movieId = movieId
        )
      }
    }
  }
}