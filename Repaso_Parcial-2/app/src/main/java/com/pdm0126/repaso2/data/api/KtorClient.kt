package com.pdm0126.repaso2.data.api

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClient {
  const val BASE_URL = "https://jsonplaceholder.typicode.com/"
  val client = HttpClient(io.ktor.client.engine.okhttp.OkHttp) {

    install(ContentNegotiation) {
      json(Json {
        ignoreUnknownKeys = true
      })
    }

    defaultRequest {
      url(BASE_URL)
      header(HttpHeaders.Accept, "application/json")
    }
  }
}