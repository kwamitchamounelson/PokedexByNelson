package com.nelson.pokedexbynelson.network

import com.google.firebase.firestore.QuerySnapshot
import com.nelson.pokedexbynelson.model.Pokemon
import com.nelson.pokedexbynelson.utils.Resource
import timber.log.Timber

abstract class BaseDataSource {

    protected suspend fun getResult(call: suspend () -> List<Pokemon>?): Resource<List<Pokemon>> {
        try {
            val response = call()
            if (response!=null) {
                return Resource.success(response)
            }
            return error("Error fecthing data")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        Timber.d(message)
        return Resource.error("Network call has failed for a following reason: $message")
    }

}