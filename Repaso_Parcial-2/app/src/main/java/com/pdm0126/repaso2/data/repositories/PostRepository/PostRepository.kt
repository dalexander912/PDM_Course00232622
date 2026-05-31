package com.pdm0126.repaso2.data.repositories.PostRepository

import com.pdm0126.repaso2.data.api.dto.PostDTO
import com.pdm0126.repaso2.models.Post

interface PostRepository {
  suspend fun GetPostById(id: Int) : Result<Post>
  suspend fun CreatePost(title: String, body: String) : Result<Post>
  suspend fun GetAllPosts() : Result<List<Post>>
}