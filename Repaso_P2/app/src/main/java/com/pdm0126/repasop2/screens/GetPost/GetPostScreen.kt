package com.pdm0126.repasop2.screens.GetPost

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GetPostScreen(
  viewModel: GetPostViewModel = viewModel(),
  navigateBack: () -> Unit
) {
  val post by viewModel.post.collectAsState()
  val error by viewModel.error.collectAsState()
  val loading by viewModel.loading.collectAsState()
  var idInput by rememberSaveable { mutableStateOf("") }
  val id = idInput.toIntOrNull() ?: 0

  Column(
    modifier = Modifier
      .safeContentPadding()
      .fillMaxSize()
      .padding(16.dp)
  ) {
    Button(navigateBack) { Text("Volver") }
    Spacer(Modifier.height(32.dp))

    Text("Ver post")
    Spacer(Modifier.height(8.dp))

    TextField(
      value = idInput,
      onValueChange = { idInput = it },
      label = { Text("Ingresar id de post") },
      modifier = Modifier.fillMaxWidth(),
      keyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Number
      )
    )
    Spacer(Modifier.height(8.dp))
    Button(onClick = { viewModel.loadPost(id) }) {
      Text("Cargar post")
    }
    Spacer(Modifier.height(16.dp))
    HorizontalDivider()

    if(loading) {
      CircularProgressIndicator()
    }
    post?.let {
      Text("- Post -")
      Text("User id: ${it.userId}")
      Text("Post id ${it.id}")
      Text("Title: ${it.title}")
      Text("Body: ${it.body}")
    }
    Text(error ?: "")
  }
}