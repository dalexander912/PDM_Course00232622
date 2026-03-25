package com.example.ejercicios2303

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejercicios2303.ui.theme.Ejercicios2303Theme

@Composable
fun Ejercicio2 (modifier: Modifier = Modifier) {
  Column(modifier = modifier.fillMaxHeight()) {
    Image(
      painterResource(R.drawable.bg_compose_background),
      null,
      Modifier.fillMaxWidth()
    )
    Text(
      text = stringResource(R.string.article_title),
      fontSize = 24.sp,
      modifier = Modifier.padding(16.dp)
    )
    Text(
      text = stringResource(R.string.article_text1),
      modifier = Modifier.padding(start = 16.dp, end = 16.dp),
      textAlign = TextAlign.Justify
    )
    Text(
      text = stringResource(R.string.article_text2),
      modifier = Modifier.padding(16.dp),
      textAlign = TextAlign.Justify
    )
  }
}

@Preview(showBackground = true)
@Composable
fun Ejercicio2Preview() {
  Ejercicios2303Theme {
    Ejercicio2()
  }
}