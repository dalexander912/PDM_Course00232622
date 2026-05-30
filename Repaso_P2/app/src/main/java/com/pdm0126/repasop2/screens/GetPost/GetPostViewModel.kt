package com.pdm0126.repasop2.screens.GetPost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm0126.repasop2.data.repositories.PostRepository.PostApiRepository
import com.pdm0126.repasop2.data.repositories.PostRepository.PostRepository
import com.pdm0126.repasop2.models.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GetPostViewModel: ViewModel() {
  private val postRepository: PostRepository = PostApiRepository()

  private val _post = MutableStateFlow<Post?>(null)
  val post = _post.asStateFlow()

  private val _loading = MutableStateFlow<Boolean>(false)
  val loading = _loading.asStateFlow()
  private val _error = MutableStateFlow<String?>(null)
  val error = _error.asStateFlow()

  fun loadPost(id: Int) {
    _error.value = null
    _loading.value = true
    _post.value = null

    viewModelScope.launch {
      postRepository.getPostById(id)
        .onSuccess { _post.value = it }
        .onFailure { _error.value = "Hubo un error al cargar el post" }
    }
    _loading.value = false
  }
}