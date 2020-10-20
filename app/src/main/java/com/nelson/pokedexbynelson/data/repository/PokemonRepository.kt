package com.nelson.pokedexbynelson.data.repository

import com.nelson.pokedexbynelson.data.local.PokedexDao
import com.nelson.pokedexbynelson.data.remote.PokedexRemoteDataSource
import com.nelson.pokedexbynelson.utils.performGetOperation
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val remoteDataSource: PokedexRemoteDataSource,
    private val localDataSource: PokedexDao
) {

    fun fetchPokemonList(
    ) = performGetOperation(
        databaseQuery = {
            localDataSource.getPokemonList()
        },
        networkCall = {
            remoteDataSource.fetchPokemonListFromFirestore()
        },
        saveCallResult = {
            localDataSource.insertPokemonList(it)
        },
        shouldfetchFromRemote = {
            true
        }
    )
}