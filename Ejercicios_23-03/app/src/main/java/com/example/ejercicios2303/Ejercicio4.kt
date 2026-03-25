package com.example.ejercicios2303

import android.R.attr.fontWeight
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ejercicios2303.ui.theme.Ejercicios2303Theme

@Composable
fun Cuadrante(modifier: Modifier = Modifier, title: String, description: String, bgColor: Color) {
  Column(
    modifier.fillMaxSize().background(bgColor).padding(16.dp),
    Arrangement.Center,
    Alignment.CenterHorizontally)
  {
    Text(
      text = title,
      fontWeight = FontWeight.Bold,
      modifier = Modifier.padding(bottom = 16.dp)
    )
    Text(
      text = description,
      textAlign = TextAlign.Justify
    )
  }
}

@Composable
fun Ejercicio4(modifier: Modifier = Modifier) {
  Column(modifier.fillMaxSize()) {
    Row(Modifier.weight(1f)) {
      Cuadrante(
        Modifier.weight(1f),
        stringResource(R.string.quadrant_title1),
        stringResource(R.string.quadrant_text1),
        Color(0xFFEADDFF)
      )
      Cuadrante(
        Modifier.weight(1f),
        stringResource(R.string.quadrant_title2),
        stringResource(R.string.quadrant_text2),
        Color(0xFFD0BCFF)
      )
    }
    Row(Modifier.weight(1f)) {
      Cuadrante(
        Modifier.weight(1f),
        stringResource(R.string.quadrant_title3),
        stringResource(R.string.quadrant_text3),
        Color(0xFFB69DF8)
      )
      Cuadrante(
        Modifier.weight(1f),
        stringResource(R.string.quadrant_title4),
        stringResource(R.string.quadrant_text4),
        Color(0xFFF6EDFF)
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
fun Ejercicio4Preview() {
  Ejercicios2303Theme {
    Ejercicio4()
  }
}