package com.pdm0126.ejercicios2204.data.api

import com.pdm0126.ejercicios2204.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClient {
  private const val BASE_URL = "https://api.themoviedb.org/3/"
  private const val API_KEY = BuildConfig.TMDB_TOKEN

  val client = HttpClient(OkHttp) {

    // Parseo automático de JSON
    install(ContentNegotiation) {
      json(Json {
        ignoreUnknownKeys = true
      })
    }

    // Configuración aplicada a todas las peticiones
    defaultRequest {
      url(BASE_URL)
      header(HttpHeaders.Authorization, "Bearer $API_KEY")
      header(HttpHeaders.Accept, "application/json")
    }
  }
}