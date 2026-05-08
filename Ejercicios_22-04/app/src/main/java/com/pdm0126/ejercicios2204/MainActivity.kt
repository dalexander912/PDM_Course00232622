package com.pdm0126.ejercicios2204

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.pdm0126.ejercicios2204.ui.screens.MovieDetailScreen
import com.pdm0126.ejercicios2204.ui.screens.MovieListScreen
import com.pdm0126.ejercicios2204.ui.theme.Ejercicios2204Theme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      Ejercicios2204Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          MovieApp(
            modifier = Modifier.padding(innerPadding)
          )
        }
      }
    }
  }
}

@Composable
fun MovieApp(modifier: Modifier = Modifier) {
  val backStack = rememberNavBackStack(Routes.Home)

  NavDisplay(
    backStack = backStack,
    onBack = { backStack.removeLastOrNull() },
    entryProvider = entryProvider {
      entry<Routes.Home> {
        MovieListScreen(
          navigateToDetail = { movieId ->
            backStack.add(Routes.MovieDetail(movieId))
          }
        )
      }
      entry<Routes.MovieDetail> { key ->
        MovieDetailScreen(
          movieId = key.movieId,
          navigateBack = {
            backStack.removeLastOrNull()
          }
        )
      }
    }
  )
}

@Preview(showBackground = true)
@Composable
fun MovieAppPreview() {
  Ejercicios2204Theme {
    MovieApp()
  }
}