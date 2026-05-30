package com.pdm0126.repasop2.data.api.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreatePostRequestDTO(
  val userId: Int,
  val title: String,
  val body: String
)