package com.pdm0126.repaso2.data.api.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreatePostRequestDTO(
  val userId: Int,
  val title: String,
  val body: String
)