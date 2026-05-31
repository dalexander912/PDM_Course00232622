package com.pdm0126.repaso2.screens.GetAllScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm0126.repaso2.data.repositories.PostRepository.PostApiRepository
import com.pdm0126.repaso2.data.repositories.PostRepository.PostRepository
import com.pdm0126.repaso2.models.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GetAllViewModel : ViewModel() {
  private val postRepository: PostRepository = PostApiRepository()

  private val _posts = MutableStateFlow<List<Post>>(emptyList())
  val posts = _posts.asStateFlow()

  private val _loading = MutableStateFlow(false)
  val loading = _loading.asStateFlow()
  private val _error = MutableStateFlow<String?>(null)
  val error = _error.asStateFlow()
  private val _refreshing = MutableStateFlow(false)
  val refreshing = _refreshing.asStateFlow()

  fun getAllPosts() {
    viewModelScope.launch {
      _loading.value = true
      _error.value = null

      postRepository.GetAllPosts()
        .onSuccess { _posts.value = it }
        .onFailure { _error.value = "Error: ${it.message}" }

      _loading.value = false
    }
  }

  fun refreshAllPosts() {
    viewModelScope.launch {
      _refreshing.value = true
      _error.value = null

      postRepository.GetAllPosts()
        .onSuccess { _posts.value = it }
        .onFailure { _error.value = "Error: ${it.message}" }

      _refreshing.value = false
    }
  }
}