package com.pdm0126.repaso2.screens.GetAllScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdm0126.repaso2.components.PostCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetAllScreen(
  navigateBack: () -> Unit,
  viewModel: GetAllViewModel = viewModel()
) {
  val posts by viewModel.posts.collectAsState()
  val loading by viewModel.loading.collectAsState()
  val error by viewModel.error.collectAsState()
  val refreshing by viewModel.refreshing.collectAsState()

  LaunchedEffect(Unit) {
    viewModel.getAllPosts()
  }

  Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
      TopAppBar(
        title = { Text("Posts") },
        colors = TopAppBarDefaults.topAppBarColors(Color.DarkGray),
        navigationIcon = {
          IconButton(navigateBack) {
            Icon(Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = "")
          }
        }
      )
    }
  ) { padding ->
    PullToRefreshBox(
      isRefreshing = refreshing,
      onRefresh = { viewModel.refreshAllPosts() },
      modifier = Modifier.fillMaxSize().padding(padding)
    ) {
      Column(
        modifier = Modifier.padding(16.dp)
      ) {
        if(loading){ CircularProgressIndicator() }
        if(error != null) { Text(error ?: "") }

        if(posts.isEmpty() && !loading && (error == null)) {
          Text("No se encontraron posts")
        } else {
          LazyColumn() {
            items(posts) { post ->
              PostCard(post)
              Spacer(Modifier.height(8.dp))
            }
          }
        }
      }
    }
  }
}