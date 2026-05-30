package com.pdm0126.repasop2.data.repositories.PostRepository

import com.pdm0126.repasop2.data.api.dto.PostDTO
import com.pdm0126.repasop2.models.Post

interface PostRepository {
  suspend fun getPostById(id: Int): Result<Post>
  suspend fun createPost(title: String, body: String): Result<PostDTO>
}