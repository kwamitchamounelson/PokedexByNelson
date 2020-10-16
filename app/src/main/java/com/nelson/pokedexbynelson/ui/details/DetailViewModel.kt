
package com.nelson.pokedexbynelson.ui.details

import androidx.annotation.MainThread
import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.nelson.pokedexbynelson.base.LiveCoroutinesViewModel
import com.nelson.pokedexbynelson.model.PokemonInfo
import com.nelson.pokedexbynelson.repository.DetailRepository
import timber.log.Timber

class DetailViewModel @ViewModelInject constructor(
  private val detailRepository: DetailRepository
) : LiveCoroutinesViewModel() {

  private var pokemonFetchingLiveData: MutableLiveData<String> = MutableLiveData()
  val pokemonInfoLiveData: LiveData<PokemonInfo?>

  val isLoading: ObservableBoolean = ObservableBoolean(false)
  val toastLiveData: MutableLiveData<String> = MutableLiveData()

  init {
    Timber.d("init DetailViewModel")

    pokemonInfoLiveData = pokemonFetchingLiveData.switchMap {
      isLoading.set(true)
      launchOnViewModelScope {
        this.detailRepository.fetchPokemonInfo(
          name = it
        ).asLiveData()
      }
    }
  }

  @MainThread
  fun fetchPokemonInfo(name: String) {
    pokemonFetchingLiveData.value = name
  }
}
