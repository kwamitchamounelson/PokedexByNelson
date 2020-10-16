package com.nelson.pokedexbynelson.network

import com.google.firebase.firestore.FirebaseFirestore
import com.nelson.pokedexbynelson.model.Pokemon
import com.nelson.pokedexbynelson.model.PokemonInfo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlinx.coroutines.tasks.await

class PokedexFirestoreService @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    suspend fun fetchPokemonListFromFirestore() = flow<List<Pokemon>> {
        try {
            val data = firestore.collection(POKEMONS).get().await()
            emit(data.toObjects(Pokemon::class.java))
        } catch (e: Exception) {
            emit(listOf())
        }
    }

    suspend fun fetchPokemonInfoFromFirestore(name: String) = flow{
        try {
            val data = firestore.collection(POKEMONS_INFOS).document(name).get().await()
            emit(data.toObject(PokemonInfo::class.java))
        } catch (e: Exception) {
            emit(null)
        }
    }

    private companion object {
        const val POKEMONS = "POKEMONS"
        const val POKEMONS_INFOS = "POKEMONS_INFOS"
    }
}