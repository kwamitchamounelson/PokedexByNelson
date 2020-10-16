package com.nelson.pokedexbynelson.di

import android.app.Application
import androidx.room.Room
import com.nelson.pokedexbynelson.persistence.AppDatabase
import com.nelson.pokedexbynelson.persistence.PokemonDao
import com.nelson.pokedexbynelson.persistence.PokemonInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

  @Provides
  @Singleton
  fun provideAppDatabase(application: Application): AppDatabase {
    return Room
      .databaseBuilder(application, AppDatabase::class.java, "Pokedex.db")
      .fallbackToDestructiveMigration()
      .build()
  }

  @Provides
  @Singleton
  fun providePokemonDao(appDatabase: AppDatabase): PokemonDao {
    return appDatabase.pokemonDao()
  }

  @Provides
  @Singleton
  fun providePokemonInfoDao(appDatabase: AppDatabase): PokemonInfoDao {
    return appDatabase.pokemonInfoDao()
  }
}
