package com.example.ejercicios2303

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejercicios2303.ui.theme.Ejercicios2303Theme

@Composable
fun Ejercicio3 (modifier: Modifier = Modifier) {
  Column(
    modifier.fillMaxSize(),
    Arrangement.Center,
    Alignment.CenterHorizontally)
  {
    Image(
      painterResource(R.drawable.ic_task_completed),
      null
    )
    Text(
      text = stringResource(R.string.task_text1),
      fontWeight = FontWeight.Bold,
      modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
    )
    Text(
      text = stringResource(R.string.task_text2),
      fontSize = 16.sp
    )
  }
}

@Preview(showBackground = true)
@Composable
fun Ejercicio3Preview() {
  Ejercicios2303Theme {
    Ejercicio3()
  }
}