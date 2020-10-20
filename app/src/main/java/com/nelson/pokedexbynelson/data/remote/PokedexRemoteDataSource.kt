package com.nelson.pokedexbynelson.data.remote

import javax.inject.Inject

class PokedexRemoteDataSource @Inject constructor(
    private val pokedexFirestoreService: PokedexFirestoreService
) : BaseDataSource() {

    suspend fun fetchPokemonListFromFirestore() = getResult {
        pokedexFirestoreService.fetchPokemonListFromFirestore()
    }

/*    suspend fun fetchPokemonInfoFromFirestore(
        name: String
    ) = getResult { pokedexFirestoreService.fetchPokemonInfoFromFirestore(name = name) }*/
}
