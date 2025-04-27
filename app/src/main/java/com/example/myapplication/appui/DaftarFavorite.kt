package com.example.myapplication.appui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.myapplication.models.ListFavorite
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class DaftarFavorite : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.navigationBars())
        controller.hide(WindowInsetsCompat.Type.statusBars())
        enableEdgeToEdge()
        setContent {
            TampilFavorite()
        }
    }
}


@Composable
fun TampilFavorite(listFavorite: ListFavorite = viewModel()) {
    val favorite by listFavorite.favorites.collectAsState()
    val context = LocalContext.current
    val auth : FirebaseAuth = Firebase.auth
    val userid = auth.currentUser?.uid


    // State untuk mengontrol dialog
    val openDialog = remember { mutableStateOf(false) }
    val selectedItemId = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Resep Favorite") },
                backgroundColor = Color(0xFFFFA726),
                navigationIcon = {
                    IconButton(onClick = {
                        val intent = Intent(context, DaftarMakananPage::class.java)
                        context.startActivity(intent)
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "back")
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigation(backgroundColor = Color(0xFFFFA726)) {
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Home, null) },
                    label = { Text("Beranda") },
                    selected = true,
                    onClick = {
                        val intent = Intent(context, DaftarMakananPage::class.java)
                        context.startActivity(intent)
                    }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Search, null) },
                    label = { Text("Cari") },
                    selected = false,
                    onClick = {
                        val intent = Intent(context, DaftarMakananPage::class.java)
                        context.startActivity(intent)
                    }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Favorite, null) },
                    label = { Text("Disukai") },
                    selected = false,
                    onClick = {

                    }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Person, null) },
                    label = { Text("Profil") },
                    selected = false,
                    onClick = {
                        val intent = Intent(context, Profile::class.java)
                        context.startActivity(intent)
                    }
                )
            }
        }

    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .padding(paddingValues)
        ) {
            items(favorite) { item ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(modifier = Modifier.padding(8.dp)) {
                        // Recipe image
                        AsyncImage(
                            model = item.gambar,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(100.dp)
                                .width(100.dp)
                                .background(Color.Gray)
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        // Recipe details
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = item.namaMakanan ?: "Tidak ada resep yang disukai",
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            // Rating
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "Rating",
                                    tint = Color.Yellow,
                                    modifier = Modifier.size(16.dp)
                                )
                                Text("4.5", color = Color.Gray)
                                Spacer(modifier = Modifier.width(10.dp))
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Hapus",
                                    tint = Color.Red,
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clickable {
                                            // Set item id to be deleted
                                            selectedItemId.value = item.idMakanan.toString()
                                            openDialog.value = true
                                        }
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Icon(
                                    Icons.Default.Info,
                                    contentDescription = null,
                                    tint = Color.Blue,
                                    modifier = Modifier
                                        .size(16.dp)
                                        .clickable {
                                            val intent = Intent(context, DetailMakanan::class.java)
                                            intent.putExtra("id", item.idMakanan.toString())
                                            context.startActivity(intent)

                                        }
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp)) // Space between items
            }
        }

        // Dialog untuk konfirmasi hapus
        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = { openDialog.value = false },
                title = { Text("Hapus Resep") },
                text = { Text("Apakah Anda yakin ingin menghapus resep ini?") },
                confirmButton = {
                    Button(

                        onClick = {
                        // Menghapus data dari Firebase
                        if (userid != null) {
                            listFavorite.delete(userid, selectedItemId.value)
                        }
                        Toast.makeText(context, "Batal suka", Toast.LENGTH_SHORT).show()
                        openDialog.value = false

                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFA726),
                            contentColor = Color.White)
                        )
                    {
                        Text("Hapus")
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        openDialog.value = false

                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFA726),
                            contentColor = Color.White
                        )
                    )
                    {
                        Text("Batal")
                    }
                }
            )
        }
    }
}
