package com.pdm0126.repasop2.screens.CreatePost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm0126.repasop2.data.repositories.PostRepository.PostApiRepository
import com.pdm0126.repasop2.data.repositories.PostRepository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreatePostViewModel: ViewModel() {
  val postRepository: PostRepository = PostApiRepository()

  private val _saving = MutableStateFlow(false)
  val saving = _saving.asStateFlow()

  private val _saveMessage = MutableStateFlow<String?>(null)
  val saveMessage = _saveMessage.asStateFlow()

  fun createPost(title: String, body: String) {
    viewModelScope.launch {
      _saving.value = true
      _saveMessage.value = null

      postRepository.createPost(title, body)
        .onSuccess { _saveMessage.value = "Post creado con id: ${it.id}" }
        .onFailure { _saveMessage.value = "Error al crear post" }

      _saving.value = false
    }
  }
}