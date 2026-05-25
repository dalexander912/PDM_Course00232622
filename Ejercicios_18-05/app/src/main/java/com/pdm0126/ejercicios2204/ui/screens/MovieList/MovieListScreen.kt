package com.pdm0126.ejercicios2204.ui.screens.MovieList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdm0126.ejercicios2204.AppScaffold
import com.pdm0126.ejercicios2204.ui.components.MovieItem

@Composable
fun MovieListScreen(
  navigateToDetail: (Int) -> Unit,
  viewModel: MovieListViewModel = viewModel()
) {
  val movies by viewModel.movies.collectAsState()
  val upcomingMovies by viewModel.upcomingMovies.collectAsState()
  val loading by viewModel.loading.collectAsState()

  var selectedIndex by remember { mutableIntStateOf(0) }
  val options = listOf("Popular", "Upcoming")

  if (loading) {
    AppScaffold(title = "Movies") { padding ->
      CircularProgressIndicator(modifier = Modifier.padding(padding))
    }
    return
  }

  AppScaffold(title = "Movies") { padding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(padding)
        .padding(16.dp)
    ) {
      SingleChoiceSegmentedButtonRow(
        modifier = Modifier.fillMaxWidth()
      ) {
        options.forEachIndexed { index, label ->
          SegmentedButton(
            shape = SegmentedButtonDefaults.itemShape(
              index = index,
              count = options.size
            ),
            onClick = { selectedIndex = index },
            selected = index == selectedIndex,
            label = { Text(label) }
          )
        }
      }
      Spacer(Modifier.height(8.dp))
      LazyColumn {
        items(
          when(selectedIndex) {
            0 -> movies
            1 -> upcomingMovies
            else -> movies
          }
        ) { movie ->
          MovieItem(
            movie = movie,
            onClick = { navigateToDetail(movie.id) }
          )
          Spacer(modifier = Modifier.height(12.dp))
        }
      }
    }
  }
}