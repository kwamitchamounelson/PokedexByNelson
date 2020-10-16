
package com.nelson.pokedexbynelson.ui.details

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.nelson.pokedexbynelson.R
import com.nelson.pokedexbynelson.base.DataBindingActivity
import com.nelson.pokedexbynelson.databinding.ActivityDetailBinding
import com.nelson.pokedexbynelson.extensions.onTransformationEndContainerApplyParams
import com.nelson.pokedexbynelson.model.Pokemon
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : DataBindingActivity() {

  @VisibleForTesting val viewModel: DetailViewModel by viewModels()

  private val binding: ActivityDetailBinding by binding(R.layout.activity_detail)

  override fun onCreate(savedInstanceState: Bundle?) {
    onTransformationEndContainerApplyParams()
    super.onCreate(savedInstanceState)
    val pokemonItem: Pokemon = requireNotNull(intent.getParcelableExtra(EXTRA_POKEMON))
    binding.apply {
      pokemon = pokemonItem
      lifecycleOwner = this@DetailActivity
      vm = viewModel.apply { fetchPokemonInfo(pokemonItem.name) }
    }
  }

  companion object {

    @VisibleForTesting const val EXTRA_POKEMON = "EXTRA_POKEMON"

    fun startActivity(transformationLayout: TransformationLayout, pokemon: Pokemon) {
      val context = transformationLayout.context
      if (context is Activity) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(EXTRA_POKEMON, pokemon)
        TransformationCompat.startActivity(transformationLayout, intent)
      }
    }
  }
}
