package com.example.myapplication.models

import com.google.gson.annotations.SerializedName

data class ResponseFilterMakanan(
    @SerializedName("meals") val filterMakanan : List<ListMakananCategory>
)
