
package com.nelson.pokedexbynelson.repository

import androidx.annotation.WorkerThread
import com.nelson.pokedexbynelson.network.PokedexFirestoreClient
import com.nelson.pokedexbynelson.persistence.PokemonInfoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailRepository @Inject constructor(
  private val pokemonFirestoreClient: PokedexFirestoreClient,
  private val pokemonInfoDao: PokemonInfoDao
) {

  @WorkerThread
  suspend fun fetchPokemonInfo(
    name: String
  ) = pokemonFirestoreClient.fetchPokemonInfoFromFirestore(name).flowOn(Dispatchers.IO)
}
