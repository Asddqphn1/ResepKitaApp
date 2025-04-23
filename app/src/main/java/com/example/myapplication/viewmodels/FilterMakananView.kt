package com.example.myapplication.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.Retrofitins
import com.example.myapplication.models.ListMakananCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FilterMakananView : ViewModel(){
    private val _filterMakaan = MutableStateFlow<List<ListMakananCategory>>(emptyList())
    val filterMakaan : StateFlow<List<ListMakananCategory>> = _filterMakaan



    fun fetchmakananfilter(kategori : String) {
        viewModelScope.launch {
            try {
                val response = Retrofitins.api.getMakananByCatgory(kategori)
                _filterMakaan.value = response.filterMakanan
            }catch (e: Exception){
                Log.e("DaftarMakananView", "gagal ambil data")
            }
        }
    }
}
