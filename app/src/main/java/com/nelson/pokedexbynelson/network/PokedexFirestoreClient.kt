package com.nelson.pokedexbynelson.network

import javax.inject.Inject

class PokedexFirestoreClient @Inject constructor(
    private val pokedexFirestoreService: PokedexFirestoreService
) : BaseDataSource() {

    suspend fun fetchPokemonListFromFirestore() = getResult {
        pokedexFirestoreService.fetchPokemonListFromFirestore()
    }

    suspend fun fetchPokemonInfoFromFirestore(
        name: String
    ) = pokedexFirestoreService.fetchPokemonInfoFromFirestore(
        name = name
    )
}
