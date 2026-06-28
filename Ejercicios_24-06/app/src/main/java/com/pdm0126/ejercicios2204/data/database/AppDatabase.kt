package com.pdm0126.ejercicios2204.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pdm0126.ejercicios2204.data.database.dao.MovieDao
import com.pdm0126.ejercicios2204.data.database.entities.MovieEntity

@Database(
  entities = [MovieEntity::class],
  version = 1,
  exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

  abstract fun movieDao(): MovieDao

  companion object {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
      return INSTANCE ?: synchronized(this) {
        Room.databaseBuilder(
          context = context.applicationContext,
          klass = AppDatabase::class.java,
          name = "movieapp_database"
        )
          .fallbackToDestructiveMigration(false)
          .build()
          .also { INSTANCE = it }
      }
    }
  }
}