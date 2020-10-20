package com.nelson.pokedexbynelson.data.remote

import com.nelson.pokedexbynelson.utils.FirebaseResponseType
import com.nelson.pokedexbynelson.utils.Resource
import retrofit2.Response
import timber.log.Timber

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> FirebaseResponseType<T>): Resource<T> {
        return when (val response = call()) {
            is FirebaseResponseType.Success -> {
                Resource.success(response.body)
            }
            is FirebaseResponseType.Error -> {
                error(response.errorMessage.message ?: "Error")
            }
        }
    }

    private fun <T> error(message: String): Resource<T> {
        Timber.d(message)
        return Resource.error("Network call has failed for a following reason: $message")
    }

}