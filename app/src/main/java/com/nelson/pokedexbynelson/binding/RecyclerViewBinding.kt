package com.nelson.pokedexbynelson.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.baserecyclerviewadapter.RecyclerViewPaginator
import com.nelson.pokedexbynelson.model.Pokemon
import com.nelson.pokedexbynelson.ui.adapter.PokemonAdapter
import com.nelson.pokedexbynelson.ui.main.MainViewModel
import com.nelson.pokedexbynelson.utils.Resource
import com.skydoves.whatif.whatIfNotNullAs
import com.skydoves.whatif.whatIfNotNullOrEmpty

@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
  view.adapter = adapter
}

@BindingAdapter("paginationPokemonList")
fun paginationPokemonList(view: RecyclerView, viewModel: MainViewModel) {
  RecyclerViewPaginator(
    recyclerView = view,
    isLoading = { viewModel.isLoading.get() },
    loadMore = { viewModel.fetchPokemonList(it) },
    onLast = { false }
  ).run {
    threshold = 8
  }
}

@BindingAdapter("adapterPokemonList")
fun bindAdapterPokemonList(view: RecyclerView, pokemonList: Resource<List<Pokemon>>?) {
  pokemonList.whatIfNotNullAs<List<Pokemon>> {
    (view.adapter as? PokemonAdapter)?.addPokemonList(it)
  }
}
