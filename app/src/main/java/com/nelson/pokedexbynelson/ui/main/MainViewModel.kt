
package com.nelson.pokedexbynelson.ui.main

import androidx.annotation.MainThread
import androidx.databinding.ObservableBoolean
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.nelson.pokedexbynelson.base.LiveCoroutinesViewModel
import com.nelson.pokedexbynelson.model.Pokemon
import com.nelson.pokedexbynelson.repository.MainRepository
import timber.log.Timber

class MainViewModel @ViewModelInject constructor(
  private val mainRepository: MainRepository,
  @Assisted private val savedStateHandle: SavedStateHandle
) : LiveCoroutinesViewModel() {

  private var pokemonFetchingLiveData: MutableLiveData<Int> = MutableLiveData()
  val pokemonListLiveData: LiveData<List<Pokemon>>

  private val _toastLiveData: MutableLiveData<String> = MutableLiveData()
  val toastLiveData: LiveData<String> get() = _toastLiveData

  val isLoading: ObservableBoolean = ObservableBoolean(false)

  init {
    Timber.d("init MainViewModel")

    pokemonListLiveData = pokemonFetchingLiveData.switchMap {
      isLoading.set(true)
      launchOnViewModelScope {
        this.mainRepository.fetchPokemonList().asLiveData()
      }
    }
  }

  @MainThread
  fun fetchPokemonList(page: Int) {
    pokemonFetchingLiveData.value = page
  }
}
