package com.nelson.pokedexbynelson.network

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.nelson.pokedexbynelson.model.Pokemon
import com.nelson.pokedexbynelson.model.PokemonInfo
import com.nelson.pokedexbynelson.utils.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlinx.coroutines.tasks.await

class PokedexFirestoreService @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    suspend fun fetchPokemonListFromFirestore(): List<Pokemon>? {
        return try {
            firestore.collection(POKEMONS).get().await().toObjects(Pokemon::class.java)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun fetchPokemonInfoFromFirestore(name: String) = flow {
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