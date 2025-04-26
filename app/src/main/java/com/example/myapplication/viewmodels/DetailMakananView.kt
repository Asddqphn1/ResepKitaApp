package com.example.myapplication.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.Retrofitins
import com.example.myapplication.models.DetailResep
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailMakananView : ViewModel() {
    private val _detail = MutableStateFlow<List<DetailResep>>(emptyList())
    val detail : StateFlow<List<DetailResep>> = _detail

    fun fetchDetailMakana(idmakanan : String){
        viewModelScope.launch {
            try{
                val response = Retrofitins.api.getDetailMakanan(idmakanan)
                _detail.value = response.detail
            }catch (e : Exception){
                Log.e("detail", "Gagal ambil data: ${e.message}", e)
            }
        }
    }
}