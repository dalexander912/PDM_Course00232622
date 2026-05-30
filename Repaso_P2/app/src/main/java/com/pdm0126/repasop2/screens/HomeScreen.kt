package com.pdm0126.repasop2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
  navigateToGet: () -> Unit,
  navigateToPost: () -> Unit
) {
  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text("Repaso Parcial 2", fontSize = 32.sp)
    Spacer(Modifier.height(32.dp))

    HorizontalDivider()

    Spacer(Modifier.height(16.dp))
    Button(navigateToGet) {
      Text("Ver post")
    }
    Spacer(Modifier.height(16.dp))
    Button(navigateToPost) {
      Text("Crear post")
    }
  }
}