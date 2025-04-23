package com.example.myapplication.models

import com.google.gson.annotations.SerializedName

data class ListMakananCategory(
    @SerializedName("strMeal") val namaMakanan : String?,
    @SerializedName("strMealThumb") val thumbniail : String?,
    @SerializedName("idMeal") val idMakanan : String?
)
