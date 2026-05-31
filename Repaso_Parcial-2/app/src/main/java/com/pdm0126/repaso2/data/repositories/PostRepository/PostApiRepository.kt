package com.pdm0126.repaso2.data.repositories.PostRepository

import com.pdm0126.repaso2.data.api.KtorClient
import com.pdm0126.repaso2.data.api.dto.CreatePostRequestDTO
import com.pdm0126.repaso2.data.api.dto.PostDTO
import com.pdm0126.repaso2.data.api.dto.toModel
import com.pdm0126.repaso2.models.Post
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class PostApiRepository : PostRepository {
  override suspend fun GetPostById(id: Int): Result<Post> {
    try {
      val response : PostDTO = KtorClient.client.get("posts/$id") {
      }.body()
      return Result.success(response.toModel())

    } catch (e: Exception) {
      return Result.failure(e)
    }
  }

  override suspend fun CreatePost(title: String, body: String): Result<Post> {
    try {
      val request = CreatePostRequestDTO(
        userId = 1,
        title = title,
        body = body
      )

      val response : PostDTO = KtorClient.client.post("posts") {
        contentType(ContentType.Application.Json)
        setBody(request)
      }.body()
      return Result.success(response.toModel())

    } catch (e: Exception) {
      return Result.failure(e)
    }
  }

  override suspend fun GetAllPosts(): Result<List<Post>> {
    try {
      val response : List<PostDTO> = KtorClient.client.get("posts") {
      }.body()
      return Result.success(response.map { postDTO -> postDTO.toModel() })

    } catch (e: Exception) {
      return Result.failure(e)
    }
  }
}