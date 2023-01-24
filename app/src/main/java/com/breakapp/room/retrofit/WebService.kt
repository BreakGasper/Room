package com.breakapp.room.retrofit

import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("search.php")
    suspend fun getRickMorty(@Query("s") tragoName:String): RickMortyList

    @GET("pokemon")
    suspend fun getAllPokemon(): PokemonList
}