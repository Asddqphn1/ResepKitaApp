package com.example.myapplication.models

import androidx.compose.runtime.State

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.firebase.FirebaseRepo
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListFavorite : ViewModel() {


        private val firebaseRepository = FirebaseRepo()
        val auth = Firebase.auth
        var userid = auth.currentUser?.uid

        private val _favorites = MutableStateFlow<List<DataFavorite>>(emptyList())
        val favorites: StateFlow<List<DataFavorite>> = _favorites

        init {
            fetchFavorites()

        }

        fun fetchFavorites() {
            viewModelScope.launch {
                // Replace with the actual user ID
                val userId = userid

                userId?.let {
                    firebaseRepository.getFavorites(it) { item ->
                        _favorites.value = item

                    }
                }

            }


        }

        fun delete (userid : String, idmakanan : String){
            _favorites.value = _favorites.value.filter { it.idMakanan != idmakanan }
            firebaseRepository.deletedata(userid, idmakanan)

        }

}