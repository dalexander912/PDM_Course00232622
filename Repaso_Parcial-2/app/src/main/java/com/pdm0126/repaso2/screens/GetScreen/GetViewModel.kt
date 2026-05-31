package com.pdm0126.repaso2.screens.GetScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm0126.repaso2.data.repositories.PostRepository.PostApiRepository
import com.pdm0126.repaso2.data.repositories.PostRepository.PostRepository
import com.pdm0126.repaso2.models.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GetViewModel : ViewModel() {
  private val postRepository: PostRepository = PostApiRepository()

  private val _post = MutableStateFlow<Post?>(null)
  val post = _post.asStateFlow()

  private val _loading = MutableStateFlow(false)
  val loading = _loading.asStateFlow()
  private val _error = MutableStateFlow<String?>(null)
  val error = _error.asStateFlow()

  fun GetPostById(id: Int) {
    viewModelScope.launch {
      _loading.value = true
      _error.value = null
      _post.value = null

      postRepository.GetPostById(id)
        .onSuccess { _post.value = it }
        .onFailure { _error.value = "Error: ${it.message}" }

      _loading.value = false
    }
  }
}