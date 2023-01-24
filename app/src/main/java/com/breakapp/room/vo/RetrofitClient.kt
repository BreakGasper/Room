package com.breakapp.room.vo

import com.breakapp.room.retrofit.WebService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {

    val webservice by lazy {
        Retrofit.Builder()
             .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")

            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }

    val webservicepokemon by lazy {
        Retrofit.Builder()
            // .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}