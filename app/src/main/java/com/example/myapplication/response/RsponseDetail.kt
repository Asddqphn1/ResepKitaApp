package com.example.myapplication.response

import com.example.myapplication.models.DetailResep
import com.google.gson.annotations.SerializedName

data class RsponseDetail(
    @SerializedName("meals") val detail: List<DetailResep>
)
