package com.breakapp.room.retrofit

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.SerializedName

@Parcelize
data class RickMorty(
    @SerializedName("idDrink")
    val idTrago: String,
    @SerializedName("strDrinkThumb")
    val imagen: String = "",
    @SerializedName("strDrink")
    val nombre: String = "",
    @SerializedName("strInstructions")
    val descrpcion: String = "",
    @SerializedName("strAlcoholic")
    val hasAlcohol: String = "Non_Alcoholic"
): Parcelable

data class RickMortyList(
    @SerializedName("drinks")
    val drinkList : List<RickMorty>

)
