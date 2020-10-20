package com.nelson.pokedexbynelson.ui.pokemonList

import androidx.lifecycle.ViewModel
import androidx.hilt.lifecycle.ViewModelInject
import com.nelson.pokedexbynelson.data.repository.PokemonRepository

class PokemonListViewModel @ViewModelInject constructor(
    private val repository: PokemonRepository
) : ViewModel() {
    val pokemonList = repository.fetchPokemonList()
}