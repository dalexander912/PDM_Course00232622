package com.example.ejercicios0804

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ejercicios0804.ui.theme.Ejercicios0804Theme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      Ejercicios0804Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          tipCalculator(
            modifier = Modifier.padding(innerPadding)
          )
        }
      }
    }
  }
}

@Composable
fun tipCalculator(modifier: Modifier = Modifier) {
  var priceInput by rememberSaveable { mutableStateOf("") }
  val price = priceInput.toDoubleOrNull() ?: 0.0
  var tipPercentInput by rememberSaveable { mutableStateOf("") }
  val tipPercent = tipPercentInput.toDoubleOrNull() ?: 0.0
  var tip by rememberSaveable { mutableStateOf("") }

  Column(
    modifier = Modifier
      .safeContentPadding()
      .fillMaxSize()
      .padding(32.dp)
      .verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = "Calculadora de propina",
      modifier = Modifier.align(alignment = Alignment.Start)
    )
    TextField(
      value = priceInput,
      onValueChange = { priceInput = it },
      modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 8.dp),
      label = { Text(text = "Precio total") },
      keyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Next
      )
    )
    TextField(
      value = tipPercentInput,
      onValueChange = { tipPercentInput = it },
      modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 8.dp),
      label = { Text(text = "Porcentaje de propina") },
      keyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Next
      )
    )
    Button(onClick = { tip = calculateTip(price, tipPercent) }) {
      Text(text = "Calcular")
    }
    Spacer(modifier = Modifier.height(32.dp))
    Text(
      text = "Propina: ${tip}",
      modifier = Modifier.align(alignment = Alignment.Start),
      style = MaterialTheme.typography.displaySmall
    )
  }
}

internal fun calculateTip(price: Double, tipPercent: Double): String {
  val tip = tipPercent / 100 * price
  return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(showBackground = true)
@Composable
fun tipCalculatorPreview() {
  Ejercicios0804Theme {
    tipCalculator()
  }
}