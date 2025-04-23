package com.example.myapplication.api

import com.example.myapplication.models.DaftarMakan
import com.example.myapplication.models.ResponseFilterMakanan
import com.example.myapplication.models.ResponseMakan
import retrofit2.http.GET
import retrofit2.http.Query

interface Apiservice {
    @GET("categories.php")
    suspend fun getCategoriesMakanan() : ResponseMakan

    @GET("filter.php")
    suspend fun getMakananByCatgory(@Query("c") kategori : String) : ResponseFilterMakanan
}