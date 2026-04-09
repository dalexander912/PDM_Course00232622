package com.example.ejercicios2503

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejercicios2503.ui.theme.Ejercicios2503Theme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      Ejercicios2503Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          LemonadeApp(
            modifier = Modifier.padding(innerPadding)
          )
        }
      }
    }
  }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
  var currentStep by remember { mutableIntStateOf(1) }
  var tapsNeeded by remember { mutableIntStateOf(Random.nextInt(2,5)) }
  var currentTaps by remember { mutableIntStateOf(0) }

  val lemonImage = when(currentStep) {
    1 -> R.drawable.lemon_tree
    2 -> R.drawable.lemon_squeeze
    3 -> R.drawable.lemon_drink
    else -> R.drawable.lemon_restart
  }
  val lemonText = when(currentStep) {
    1 -> "Presiona el arbol para cortar un limon"
    2 -> "Presiona el limon para exprimirlo"
    3 -> "Presiona la limonada para beberla"
    else -> "Presiona el vaso para reiniciar"
  }

  val onImageClick:() -> Unit = {
    when(currentStep) {
      2 -> {
        currentTaps++
        if(currentTaps == tapsNeeded) {
          currentStep++
          currentTaps = 0
        }
      }
      4 -> {
        currentStep = 1
        tapsNeeded = Random.nextInt(2,5)
      }
      else -> currentStep++
    }
  }

  topBar()
  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Button(
      onClick = onImageClick,
      shape = RoundedCornerShape(24.dp)
    ) {
      Image(painterResource(lemonImage), null)
    }
    Spacer(Modifier.height(32.dp))
    Text(text = lemonText)
    Spacer(Modifier.height(16.dp))
    if(currentStep == 2) {
      Text(text = "Pasos restantes: ${tapsNeeded-currentTaps}")
    }
  }
}

@Composable
fun topBar() {
  Box(modifier = Modifier
    .fillMaxWidth()
    .background(Color.Yellow)
    .height(60.dp)) {
    Text(
      text = "Lemonade App",
      fontSize = 20.sp,
      fontWeight = FontWeight.Bold,
      modifier = Modifier.align(Alignment.Center)
    )
  }
}


@Preview(showBackground = true)
@Composable
fun LemonadeAppPreview() {
  Ejercicios2503Theme {
    LemonadeApp()
  }
}