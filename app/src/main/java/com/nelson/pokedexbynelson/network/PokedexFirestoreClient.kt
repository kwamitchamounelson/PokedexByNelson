package com.nelson.pokedexbynelson.network

import javax.inject.Inject

class PokedexFirestoreClient @Inject constructor(
    private val pokedexFirestoreService: PokedexFirestoreService
) {
    suspend fun fetchPokemonListFromFirestore(
    ) = pokedexFirestoreService.fetchPokemonListFromFirestore()

    suspend fun fetchPokemonInfoFromFirestore(
        name: String
    ) = pokedexFirestoreService.fetchPokemonInfoFromFirestore(
        name = name
    )
}
