package com.nelson.pokedexbynelson.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nelson.pokedexbynelson.data.entities.Pokemon
import com.nelson.pokedexbynelson.data.entities.PokemonInfo

@Dao
interface PokedexDao {

    @Query("SELECT * FROM Pokemon")
    fun getPokemonList(): LiveData<List<Pokemon>>

    /*@Query("SELECT * FROM PokemonInfo WHERE name = :name_")
    fun getPokemonInfo(name_: String): LiveData<PokemonInfo?>*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonList(pokemonList: List<Pokemon>)

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonInfo(pokemonInfo: PokemonInfo)*/
}
