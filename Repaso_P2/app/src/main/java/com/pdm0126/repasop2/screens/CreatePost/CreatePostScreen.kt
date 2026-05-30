package com.pdm0126.repasop2.screens.CreatePost

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CreatePostScreen(
  viewModel: CreatePostViewModel = viewModel(),
  navigateBack: () -> Unit
) {
  val saving by viewModel.saving.collectAsState()
  val saveMessage by viewModel.saveMessage.collectAsState()

  var title by rememberSaveable { mutableStateOf("") }
  var body by rememberSaveable { mutableStateOf("") }
  var isValid = title.isNotBlank() && body.isNotBlank()

  Column(
    modifier = Modifier
      .safeContentPadding()
      .fillMaxSize()
      .padding(16.dp),
  ) {
    Button(navigateBack) { Text("Volver") }
    Spacer(Modifier.height(32.dp))

    Text("Crear post")
    Spacer(Modifier.height(8.dp))

    TextField(
      value = title,
      onValueChange = { title = it },
      label = { Text("Titulo") },
      modifier = Modifier.fillMaxWidth(),
      singleLine = true
    )
    TextField(
      value = body,
      onValueChange = { body = it },
      label = { Text("Contenido") },
      modifier = Modifier.fillMaxWidth(),
      maxLines = 4
    )
    Spacer(Modifier.height(16.dp))
    Button(
      onClick = { if(isValid) { viewModel.createPost(title, body) } }
    ) {
      Text("Crear post")
    }
    Spacer(Modifier.height(16.dp))
    HorizontalDivider()

    if(saving) {
      CircularProgressIndicator()
    }
    Text(saveMessage ?: "")
  }
}