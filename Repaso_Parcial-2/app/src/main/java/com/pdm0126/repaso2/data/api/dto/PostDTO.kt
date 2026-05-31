package com.pdm0126.repaso2.data.api.dto

import com.pdm0126.repaso2.models.Post
import kotlinx.serialization.Serializable

@Serializable
data class PostDTO(
  val id: Int,
  val userId: Int,
  val title: String,
  val body: String
)

fun PostDTO.toModel() : Post {
  return Post(
    id = id,
    userId = userId,
    title = title,
    body = body
  )
}