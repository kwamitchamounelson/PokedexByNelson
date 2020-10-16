package com.nelson.pokedexbynelson.repository

import androidx.annotation.WorkerThread
import com.nelson.pokedexbynelson.network.PokedexFirestoreClient
import com.nelson.pokedexbynelson.persistence.PokemonDao
import com.nelson.pokedexbynelson.utils.performGetOperation
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val pokemonDao: PokemonDao,
    private val pokemonFirestoreClient: PokedexFirestoreClient
) {
    @WorkerThread
    suspend fun fetchPokemonList(
    ) = performGetOperation(
        databaseQuery = {
            pokemonDao.getPokemonList(0)
        },
        networkCall = {
            pokemonFirestoreClient.fetchPokemonListFromFirestore()
        },
        saveCallResult = {
            pokemonDao.insertPokemonList(it)
        }
    )
}
