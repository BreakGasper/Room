package com.breakapp.room.retrofit

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
//    @SerializedName("idDrink")
//    val idTrago: String ="",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("url")
    val url: String = "",

    ) : Parcelable

data class PokemonList(
    @SerializedName("results")
    val pokemonList: List<Pokemon>

)
