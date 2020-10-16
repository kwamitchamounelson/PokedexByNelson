package com.nelson.pokedexbynelson.repository

import androidx.annotation.WorkerThread
import com.nelson.pokedexbynelson.network.PokedexFirestoreClient
import com.nelson.pokedexbynelson.persistence.PokemonDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val pokemonDao: PokemonDao,
    private val pokemonFirestoreClient: PokedexFirestoreClient
) {

    @WorkerThread
    suspend fun fetchPokemonList(
    ) = pokemonFirestoreClient.fetchPokemonListFromFirestore()
        .flowOn(Dispatchers.IO)
}
