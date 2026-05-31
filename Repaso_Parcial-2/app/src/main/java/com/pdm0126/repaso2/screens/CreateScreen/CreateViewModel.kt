package com.pdm0126.repaso2.screens.CreateScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm0126.repaso2.data.repositories.PostRepository.PostApiRepository
import com.pdm0126.repaso2.data.repositories.PostRepository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreateViewModel : ViewModel() {
  private val postRepository: PostRepository = PostApiRepository()

  private val _saveMessage = MutableStateFlow<String?>(null)
  val saveMessage = _saveMessage.asStateFlow()

  private val _loading = MutableStateFlow(false)
  val loading = _loading.asStateFlow()

  fun CreatePost(title: String, body: String) {
    viewModelScope.launch {
      _loading.value = true
      _saveMessage.value = null

      postRepository.CreatePost(title, body)
        .onSuccess { _saveMessage.value = "Post creado con id: ${it.id}" }
        .onFailure { _saveMessage.value = "Error: ${it.message}" }

      _loading.value = false
    }
  }
}