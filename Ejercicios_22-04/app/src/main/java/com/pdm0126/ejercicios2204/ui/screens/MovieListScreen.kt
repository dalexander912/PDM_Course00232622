package com.pdm0126.ejercicios2204.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pdm0126.ejercicios2204.AppScaffold
import com.pdm0126.ejercicios2204.data.dummyMovies
import com.pdm0126.ejercicios2204.ui.components.MovieItem

@Composable
fun MovieListScreen(
  navigateToDetail: (Int) -> Unit
) {
  AppScaffold(title = "Movies") { padding ->
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .padding(padding)
        .padding(16.dp),
    ) {
      items(dummyMovies) { movie ->
        MovieItem(
          movie = movie,
          onClick = { navigateToDetail(movie.id) }
        )
        Spacer(modifier = Modifier.height(12.dp))
      }
    }
  }
}