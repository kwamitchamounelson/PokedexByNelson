package com.nelson.pokedexbynelson.di

import com.google.firebase.firestore.FirebaseFirestore
import com.nelson.pokedexbynelson.network.PokedexFirestoreClient
import com.nelson.pokedexbynelson.network.PokedexFirestoreService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun providePokedexFirestoreService(firestore: FirebaseFirestore): PokedexFirestoreService {
        return PokedexFirestoreService(firestore)
    }

    @Singleton
    @Provides
    fun providePokedexFirestoreClient(pokedexFirestoreService: PokedexFirestoreService): PokedexFirestoreClient {
        return PokedexFirestoreClient(pokedexFirestoreService)
    }
}
