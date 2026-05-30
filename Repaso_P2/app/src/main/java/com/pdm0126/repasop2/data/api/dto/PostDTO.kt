package com.pdm0126.repasop2.data.api.dto

import com.pdm0126.repasop2.models.Post
import kotlinx.serialization.Serializable

@Serializable
data class PostDTO (
  val userId: Int,
  val id: Int,
  val title: String,
  val body: String
)

fun PostDTO.toModel(): Post {
  return Post(
    userId = userId,
    id = id,
    title = title,
    body = body
  )
}