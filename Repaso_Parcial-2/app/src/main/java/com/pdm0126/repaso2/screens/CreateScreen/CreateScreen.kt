package com.pdm0126.repaso2.screens.CreateScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateScreen(
  navigateBack: () -> Unit,
  viewModel: CreateViewModel = viewModel()
) {
  val saveMessage by viewModel.saveMessage.collectAsState()
  val loading by viewModel.loading.collectAsState()

  var title by rememberSaveable { mutableStateOf("") }
  var body by rememberSaveable { mutableStateOf("") }
  var isValid = title.isNotBlank() && body.isNotBlank()

  Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
      TopAppBar(
        title = { Text("Crear Post") },
        colors = TopAppBarDefaults.topAppBarColors(Color.DarkGray),
        navigationIcon = {
          IconButton(navigateBack) {
            Icon(Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = "")
          }
        }
      )
    }
  ) { padding ->
    Column(
      modifier = Modifier.padding(padding).padding(8.dp)
    ) {
      TextField(
        label = { Text("Ingresar titulo") },
        value = title,
        onValueChange = { title = it },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
      )
      TextField(
        label = { Text("Ingresar cuerpo") },
        value = body,
        onValueChange = { body = it },
        modifier = Modifier.fillMaxWidth()
      )
      Spacer(Modifier.height(16.dp))
      Button(
        onClick = { viewModel.CreatePost(title, body) },
        enabled = isValid
      ) {
        Text("Crear")
      }

      Spacer(Modifier.height(32.dp))
      HorizontalDivider(thickness = 4.dp)
      Spacer(Modifier.height(32.dp))

      if(loading){ CircularProgressIndicator() }
      Text(saveMessage ?: "")
    }
  }
}