package com.nelson.pokedexbynelson.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.nelson.pokedexbynelson.data.entities.Pokemon
import com.nelson.pokedexbynelson.utils.FirebaseResponseType
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PokedexFirestoreService @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    suspend fun fetchPokemonListFromFirestore(): FirebaseResponseType<List<Pokemon>> {
        return try {
            val data = firestore.collection(POKEMONS).get().await().toObjects(Pokemon::class.java)
            FirebaseResponseType.Success(data)
        } catch (e: Exception) {
            FirebaseResponseType.Error(e)
        }
    }

    /*suspend fun fetchPokemonInfoFromFirestore(name: String): PokemonInfo? {
        return try {
            val data = firestore.collection(POKEMONS_INFOS).document(name).get().await()
            data.toObject(PokemonInfo::class.java)
        } catch (e: Exception) {
            null
        }
    }*/

    private companion object {
        const val POKEMONS = "POKEMONS"
        const val POKEMONS_INFOS = "POKEMONS_INFOS"
    }
}