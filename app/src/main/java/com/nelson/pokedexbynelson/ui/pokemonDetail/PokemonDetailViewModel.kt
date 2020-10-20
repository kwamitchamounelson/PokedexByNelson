package com.nelson.pokedexbynelson.ui.pokemonDetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.nelson.pokedexbynelson.data.repository.PokemonRepository

class PokemonDetailViewModel @ViewModelInject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

}