
package com.nelson.pokedexbynelson.ui.adapter

import android.os.SystemClock
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.nelson.pokedexbynelson.R
import com.nelson.pokedexbynelson.databinding.ItemPokemonBinding
import com.nelson.pokedexbynelson.model.Pokemon
import com.nelson.pokedexbynelson.ui.details.DetailActivity

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

  private val items: MutableList<Pokemon> = mutableListOf()
  private var onClickedAt = 0L

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val binding =
      DataBindingUtil.inflate<ItemPokemonBinding>(inflater, R.layout.item_pokemon, parent, false)
    return PokemonViewHolder(binding).apply {
      binding.root.setOnClickListener {
        val position = adapterPosition.takeIf { it != NO_POSITION }
          ?: return@setOnClickListener
        val currentClickedAt = SystemClock.elapsedRealtime()
        if (currentClickedAt - onClickedAt > binding.transformationLayout.duration) {
          DetailActivity.startActivity(binding.transformationLayout, items[position])
          onClickedAt = currentClickedAt
        }
      }
    }
  }

  fun addPokemonList(pokemonList: List<Pokemon>) {
    val previous = items.size
    items.addAll(pokemonList)
    notifyItemRangeChanged(previous, pokemonList.size)
  }

  override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
    holder.binding.apply {
      pokemon = items[position]
      executePendingBindings()
    }
  }

  override fun getItemCount() = items.size

  class PokemonViewHolder(val binding: ItemPokemonBinding) :
    RecyclerView.ViewHolder(binding.root)
}
