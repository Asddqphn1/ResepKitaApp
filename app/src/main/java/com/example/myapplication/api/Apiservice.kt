package com.example.myapplication.api

import com.example.myapplication.response.ResponseFilterMakanan
import com.example.myapplication.response.ResponseMakan
import com.example.myapplication.response.RsponseDetail
import retrofit2.http.GET
import retrofit2.http.Query

interface Apiservice {
    @GET("categories.php")
    suspend fun getCategoriesMakanan() : ResponseMakan

    @GET("filter.php")
    suspend fun getMakananByCatgory(@Query("c") kategori : String) : ResponseFilterMakanan

    @GET("lookup.php")
    suspend fun getDetailMakanan(@Query("i") id : String) : RsponseDetail
}