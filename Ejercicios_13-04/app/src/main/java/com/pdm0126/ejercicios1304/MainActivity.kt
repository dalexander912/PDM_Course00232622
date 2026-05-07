package com.pdm0126.ejercicios1304

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pdm0126.ejercicios1304.ui.theme.Ejercicios1304Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      Ejercicios1304Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          MyFirstScreen(
            modifier = Modifier.padding(innerPadding)
          )
        }
      }
    }
  }
}

@Composable
fun MyFirstScreen(modifier: Modifier = Modifier) {
  val snackbarHostState = remember { SnackbarHostState() }
  val scope = rememberCoroutineScope()

  var isSheetVisible by rememberSaveable { mutableStateOf(false) }

  val onShowSnackBar: () -> Unit = {
    scope.launch {
      snackbarHostState.showSnackbar("Botón presionado")
    }
  }

  AppScaffold(
    modifier = modifier,
    title = "Top app bar",
    bottomBarText = "Bottom app bar",
    snackbarHostState = snackbarHostState,
    onFabClick = { isSheetVisible = true }
  ) { innerPadding ->

    Column(modifier = Modifier.padding(innerPadding)) {
      Text("Hello World")
      Button(onClick = onShowSnackBar) {
        Text("Mostrar Snackbar")
      }
    }

    CustomModalBottomSheet(
      isVisible = isSheetVisible,
      onDismiss = { isSheetVisible = false }
    ) {
      Text("Opción 1")
      Text("Opción 2")
      Text("Opción 3")
    }
  }
}

@Preview(showBackground = true)
@Composable
fun MyFirstScreenPreview() {
  Ejercicios1304Theme {
    MyFirstScreen()
  }
}