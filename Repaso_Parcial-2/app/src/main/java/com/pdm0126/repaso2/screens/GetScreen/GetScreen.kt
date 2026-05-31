package com.pdm0126.repaso2.screens.GetScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdm0126.repaso2.components.PostCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetScreen(
  navigateBack: () -> Unit,
  viewModel: GetViewModel = viewModel()
) {
  val post by viewModel.post.collectAsState()
  val loading by viewModel.loading.collectAsState()
  val error by viewModel.error.collectAsState()

  var idInput by rememberSaveable { mutableStateOf("") }
  val id = idInput.toIntOrNull() ?: 0

  val isValid = idInput.isNotBlank()

  Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
      TopAppBar(
        title = { Text("Obtener Post") },
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
        label = { Text("Ingresar id") },
        value = idInput,
        onValueChange = { idInput = it },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(
          keyboardType = KeyboardType.Number,
          imeAction = ImeAction.Done
        ),
        singleLine = true
      )
      Spacer(Modifier.height(16.dp))
      Button(
        onClick = { viewModel.GetPostById(id) },
        enabled = isValid
      ) {
        Text("Buscar")
      }

      Spacer(Modifier.height(32.dp))
      HorizontalDivider(thickness = 4.dp)
      Spacer(Modifier.height(32.dp))

      if(loading){ CircularProgressIndicator() }
      if(error != null) { Text(error ?: "") }

      post?.let { PostCard(it) }
    }
  }
}