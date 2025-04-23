package com.example.myapplication.models

import com.google.gson.annotations.SerializedName

data class DaftarMakan(
    @SerializedName("idCategory") val id: String?,
    @SerializedName("strCategory") val kategori: String?,
    @SerializedName("strCategoryDescription") val description: String?,
    @SerializedName("strCategoryThumb") val thumb: String?
)
