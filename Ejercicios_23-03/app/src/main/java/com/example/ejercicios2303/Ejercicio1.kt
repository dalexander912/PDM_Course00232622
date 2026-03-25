package com.example.ejercicios2303

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.ejercicios2303.ui.theme.Ejercicios2303Theme

@Composable
fun Ejercicio1 (modifier: Modifier = Modifier) {
  Column(modifier.fillMaxHeight()) {
    Box(Modifier.weight(1f).fillMaxWidth().background(color = Color.Cyan), Alignment.Center) {
      Text(text = "Ejemplo 1")
    }
    Row(Modifier.weight(1f).fillMaxWidth()) {
      Box(Modifier.weight(1f).fillMaxHeight().background(color = Color.Red), Alignment.Center) {
        Text(text = "Ejemplo 2")
      }
      Box(Modifier.weight(1f).fillMaxHeight().background(color = Color.Green), Alignment.TopCenter) {
        Text(text = "Ejemplo 3")
      }
    }
    Box(Modifier.weight(1f).fillMaxWidth().background(color = Color.Magenta), Alignment.BottomCenter) {
      Text(text = "Ejemplo 4")
    }
  }
}

@Preview(showBackground = true)
@Composable
fun Ejercicio1Preview() {
  Ejercicios2303Theme {
    Ejercicio1()
  }
}