package com.pdm0126.repaso2

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
import com.pdm0126.repaso2.screens.CreateScreen.CreateScreen
import com.pdm0126.repaso2.screens.GetAllScreen.GetAllScreen
import com.pdm0126.repaso2.screens.GetScreen.GetScreen
import com.pdm0126.repaso2.screens.HomeScreen
import com.pdm0126.repaso2.ui.theme.Repaso2Theme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      Repaso2Theme {
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
  var backStack = rememberNavBackStack(Routes.Home)

  NavDisplay(
    backStack = backStack,
    onBack = { backStack.removeLastOrNull() },
    entryProvider = entryProvider {
      entry<Routes.Home> {
        HomeScreen(
          navigateToGet = {backStack.add(Routes.GetScreen)},
          navigateToCreate = {backStack.add(Routes.CreateScreen)},
          navigateToGetAll = {backStack.add(Routes.GetAllScreen)}
        )
      }
      entry<Routes.GetScreen> {
        GetScreen(
          navigateBack = {backStack.removeLastOrNull()}
        )
      }
      entry<Routes.CreateScreen> {
        CreateScreen(
          navigateBack = {backStack.removeLastOrNull()}
        )
      }
      entry<Routes.GetAllScreen> {
        GetAllScreen(
          navigateBack = {backStack.removeLastOrNull()}
        )
      }
    }
  )
}