
package com.nelson.pokedexbynelson.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.nelson.pokedexbynelson.R
import com.nelson.pokedexbynelson.base.DataBindingActivity
import com.nelson.pokedexbynelson.databinding.ActivityMainBinding
import com.nelson.pokedexbynelson.ui.adapter.PokemonAdapter
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : DataBindingActivity() {

  @VisibleForTesting val viewModel: MainViewModel by viewModels()
  private val binding: ActivityMainBinding by binding(R.layout.activity_main)

  override fun onCreate(savedInstanceState: Bundle?) {
    onTransformationStartContainer()
    super.onCreate(savedInstanceState)
    binding.apply {
      lifecycleOwner = this@MainActivity
      adapter = PokemonAdapter()
      vm = viewModel.apply { fetchPokemonList(0) }
    }
  }
}
