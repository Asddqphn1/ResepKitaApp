package com.example.myapplication.models

import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class MakananDisukai : ViewModel(){
    val firebase : DatabaseReference = Firebase.database.reference

    fun WriteSuka (userId : String, gambar : String, namaMakana : String, idMakanan : String){
        val favoriteMakanan = DataFavorite(namaMakana, gambar, idMakanan)
        firebase.child("Users").child(userId).child("Favorite").child(idMakanan).setValue(favoriteMakanan)

    }
}



