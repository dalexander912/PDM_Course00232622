package com.pdm0126.repaso2.models

data class Post(
  val id: Int,
  val userId: Int,
  val title: String,
  val body: String
)