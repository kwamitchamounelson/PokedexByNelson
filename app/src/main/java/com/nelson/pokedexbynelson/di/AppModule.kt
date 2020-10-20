package com.nelson.pokedexbynelson.di

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.nelson.pokedexbynelson.data.local.AppDatabase
import com.nelson.pokedexbynelson.data.local.PokedexDao
import com.nelson.pokedexbynelson.data.remote.PokedexFirestoreService
import com.nelson.pokedexbynelson.data.remote.PokedexRemoteDataSource
import com.nelson.pokedexbynelson.data.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun providePokedexFirestoreService(firestore: FirebaseFirestore): PokedexFirestoreService {
        return PokedexFirestoreService(
            firestore
        )
    }

    @Singleton
    @Provides
    fun providePokedexRemoteDataSource(pokedexFirestoreService: PokedexFirestoreService): PokedexRemoteDataSource {
        return PokedexRemoteDataSource(
            pokedexFirestoreService
        )
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.pokedexDao()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: PokedexRemoteDataSource,
        localDataSource: PokedexDao
    ) =
        PokemonRepository(remoteDataSource, localDataSource)
}