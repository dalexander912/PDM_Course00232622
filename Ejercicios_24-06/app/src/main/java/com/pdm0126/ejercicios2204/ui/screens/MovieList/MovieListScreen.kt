package com.pdm0126.ejercicios2204.ui.screens.MovieList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdm0126.ejercicios2204.AppScaffold
import com.pdm0126.ejercicios2204.ui.components.MovieItem

@Composable
fun MovieListScreen(
  navigateToDetail: (Int) -> Unit,
  viewModel: MovieListViewModel = viewModel(factory = MovieListViewModel.Factory)
) {
  val movies by viewModel.movies.collectAsStateWithLifecycle()
  val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()
  val error by viewModel.error.collectAsStateWithLifecycle()

  // 1. Cargando: Room vacío y todavía esperando a la API
  if (movies.isEmpty() && isRefreshing) {
    AppScaffold(title = "Movies") { padding ->
      CircularProgressIndicator(modifier = Modifier.padding(padding))
    }
    return
  }

  // 2. Error sin cache: la API falló y no hay nada guardado
  if (movies.isEmpty() && error != null) {
    AppScaffold(title = "Movies") { padding ->
      Column(
        modifier = Modifier.fillMaxSize().padding(padding)
      ) {
        Text("$error")
        Spacer(Modifier.height(16.dp))
        Button(onClick = { viewModel.refresh() }) { Text("Reintentar") }
      }
    }
    return
  }

  // 3. Datos: hay cache (con o sin internet)
  AppScaffold(title = "Movies") { padding ->
    PullToRefreshBox(
      isRefreshing = isRefreshing,
      onRefresh = { viewModel.refresh() },
      modifier = Modifier
        .fillMaxSize()
        .padding(padding)
    ) {
      Column(
        modifier = Modifier.padding(16.dp)
      ) {
        LazyColumn {
          items(movies) { movie ->
            MovieItem(movie = movie, onClick = { navigateToDetail(movie.id) })
            Spacer(modifier = Modifier.height(12.dp))
          }
        }
      }
    }
  }
}