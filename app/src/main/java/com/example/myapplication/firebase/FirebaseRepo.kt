package com.example.myapplication.firebase

import android.util.Log
import com.example.myapplication.models.DataFavorite
import com.example.myapplication.models.Users
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FirebaseRepo {
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val usersRef: DatabaseReference = database.getReference("Users")

    fun getFavorites(userId: String, onResult: (List<DataFavorite>) -> Unit) {
        val userRef = usersRef.child(userId).child("Favorite")

        userRef.get().addOnSuccessListener { snapshot ->
            val favorites = snapshot.children.mapNotNull { it.getValue(DataFavorite::class.java) }
            onResult(favorites)
        }.addOnFailureListener { exception ->
            Log.e("Firebase", "Error fetching data: $exception")
        }
    }


    fun deletedata(userId: String, idmakanan : String){
        val userRef = usersRef.child(userId).child("Favorite").child(idmakanan)
        userRef.removeValue()
    }
}