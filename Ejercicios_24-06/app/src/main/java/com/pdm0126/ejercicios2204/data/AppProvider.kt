package com.pdm0126.ejercicios2204.data

import android.content.Context
import com.pdm0126.ejercicios2204.data.database.AppDatabase
import com.pdm0126.ejercicios2204.data.repositories.MovieRepository.MovieOfflineFirstRepository
import com.pdm0126.ejercicios2204.data.repositories.MovieRepository.MovieOfflineFirstRepositoryImpl

class AppProvider(context: Context) {

  private val appDatabase = AppDatabase.getDatabase(context)

  private val movieDao = appDatabase.movieDao()

  private val movieOfflineFirstRepository: MovieOfflineFirstRepository =
    MovieOfflineFirstRepositoryImpl(movieDao)

  fun provideMovieOfflineFirstRepository(): MovieOfflineFirstRepository {
    return movieOfflineFirstRepository
  }
}