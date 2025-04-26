package com.example.myapplication.response

import com.example.myapplication.models.ListMakananCategory
import com.google.gson.annotations.SerializedName

data class ResponseFilterMakanan(
    @SerializedName("meals") val filterMakanan : List<ListMakananCategory>
)
