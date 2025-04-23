package com.example.myapplication.viewmodels



import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.Retrofitins
import com.example.myapplication.models.DaftarMakan
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DaftarMakananView : ViewModel() {
    private val _category = MutableStateFlow<List<DaftarMakan>>(emptyList())
    val category : StateFlow<List<DaftarMakan>> = _category

    init {
        fetchmakanan()
    }

    private fun fetchmakanan() {
        viewModelScope.launch {
            try {
                val response = Retrofitins.api.getCategoriesMakanan()
                _category.value = response.categories

            }catch (e: Exception){
                Log.e("DaftarMakananView", "gagal ambil data")
            }
        }
    }
}