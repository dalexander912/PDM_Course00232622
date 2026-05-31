package com.pdm0126.repaso2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
  navigateToCreate: () -> Unit,
  navigateToGet: () -> Unit,
  navigateToGetAll: () -> Unit
) {
  Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
      TopAppBar(
        title = { Text("Repaso Parcial 2") },
        colors = TopAppBarDefaults.topAppBarColors(Color.DarkGray)
      )
    }
  ) { padding ->
    Column(
      modifier = Modifier.padding(padding).fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Button(navigateToGet) { Text("Obtener Post") }
      Spacer(Modifier.height(16.dp))
      Button(navigateToCreate) { Text("Crear Post") }
      Spacer(Modifier.height(16.dp))
      Button(navigateToGetAll) { Text("Ver todos los Posts") }
    }
  }
}