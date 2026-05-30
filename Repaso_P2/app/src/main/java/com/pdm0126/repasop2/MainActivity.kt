package com.pdm0126.repasop2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.pdm0126.repasop2.screens.CreatePost.CreatePostScreen
import com.pdm0126.repasop2.screens.GetPost.GetPostScreen
import com.pdm0126.repasop2.screens.HomeScreen
import com.pdm0126.repasop2.ui.theme.RepasoP2Theme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      RepasoP2Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          App(
            modifier = Modifier.padding(innerPadding)
          )
        }
      }
    }
  }
}

@Composable
fun App(modifier: Modifier = Modifier) {
  val backStack = rememberNavBackStack(Routes.Home)

  NavDisplay(
    backStack = backStack,
    onBack = { backStack.removeLastOrNull() },
    entryProvider = entryProvider {
      entry<Routes.Home> {
        HomeScreen(
          {backStack.add(Routes.GetScreen)},
          {backStack.add(Routes.PostScreen)}
        )
      }
      entry<Routes.GetScreen> {
        GetPostScreen(
          navigateBack = {backStack.removeLastOrNull()}
        )
      }
      entry<Routes.PostScreen> {
        CreatePostScreen(
          navigateBack = {backStack.removeLastOrNull()}
        )
      }
    }
  )
}