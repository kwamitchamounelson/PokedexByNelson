package com.nelson.pokedexbynelson.utils


sealed class FirebaseResponseType<out T> {
    data class Success<T>(val body: T) : FirebaseResponseType<T>()
    data class Error<T>(val errorMessage: Exception) : FirebaseResponseType<T>()
}