package com.pdm0126.ejercicios2204.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pdm0126.ejercicios2204.models.Movie

@Entity(tableName = "movies")
data class MovieEntity(
  @PrimaryKey val id: Int,
  val title: String,
  val originalTitle: String,
  val originalLanguage: String,
  val overview: String,
  val releaseDate: String,
  val adult: Boolean,
  val popularity: Double,
  val voteAverage: Double,
  val voteCount: Int,
  val video: Boolean,
  val backdropUrl: String,
  val posterUrl: String
)

fun MovieEntity.toModel(): Movie = Movie(
  id = id,
  title = title,
  originalTitle = originalTitle,
  originalLanguage = originalLanguage,
  overview = overview,
  releaseDate = releaseDate,
  adult = adult,
  genreIds = emptyList(),   // no lo guardamos en Room
  popularity = popularity,
  voteAverage = voteAverage,
  voteCount = voteCount,
  video = video,
  backdropUrl = backdropUrl,
  posterUrl = posterUrl
)