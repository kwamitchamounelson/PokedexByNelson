package com.skydoves.pokedex.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.skydoves.pokedex.model.Pokemon
import com.skydoves.pokedex.model.PokemonInfo
import com.skydoves.pokedex.network.PokedexClient
import com.skydoves.pokedex.persistence.PokemonInfoDao
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FirebaseUtil @Inject constructor(
        private val pokedexClient: PokedexClient
) {

    companion object {
        private val db = FirebaseFirestore.getInstance()
        const val POKEMONS = "POKEMONS"
        const val POKEMONS_INFOS = "POKEMONS_INFOS"
    }

    suspend fun savePokemonToFirestore(pokedex: Pokemon) {
        db.collection(POKEMONS)
                .document(pokedex.name)
                .set(pokedex, SetOptions.merge())
                .addOnSuccessListener {
                }
                .addOnFailureListener {

                }

        val response = pokedexClient.fetchPokemonInfo(pokedex.name)
        response.suspendOnSuccess {
            data.whatIfNotNull { response ->
                savePokemonInfosToFirestore(response)
            }
        }
    }

    fun savePokemonInfosToFirestore(pokemonInfo: PokemonInfo) {
        db.collection(POKEMONS_INFOS)
                .document(pokemonInfo.name)
                .set(pokemonInfo, SetOptions.merge())
                .addOnSuccessListener {

                }
                .addOnFailureListener {

                }
    }

    suspend fun savePokemonListToFirestore(pokedexList: List<Pokemon>) {
        pokedexList.forEach {
            savePokemonToFirestore(it)
        }
    }
}