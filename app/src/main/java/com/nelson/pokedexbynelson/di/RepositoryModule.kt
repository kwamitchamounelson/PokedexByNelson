package com.nelson.pokedexbynelson.di

import com.nelson.pokedexbynelson.network.PokedexFirestoreClient
import com.nelson.pokedexbynelson.persistence.PokemonDao
import com.nelson.pokedexbynelson.persistence.PokemonInfoDao
import com.nelson.pokedexbynelson.repository.DetailRepository
import com.nelson.pokedexbynelson.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideMainRepository(
        pokemonDao: PokemonDao,
        pokemonFirestoreClient: PokedexFirestoreClient
    ): MainRepository {
        return MainRepository(pokemonDao, pokemonFirestoreClient)
    }

    @Provides
    @ActivityRetainedScoped
    fun provideDetailRepository(
        pokemonFirestoreClient: PokedexFirestoreClient,
        pokemonInfoDao: PokemonInfoDao
    ): DetailRepository {
        return DetailRepository(pokemonFirestoreClient, pokemonInfoDao)
    }
}
