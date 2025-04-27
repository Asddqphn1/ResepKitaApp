package com.example.myapplication.appui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.auth.FirebaseAuth

class Profile : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.navigationBars())
        controller.hide(WindowInsetsCompat.Type.statusBars())
        setContent {
            ProfileScreen()
        }
    }
}

@Composable
fun ProfileScreen() {
    val context = LocalContext.current
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val userId = auth.currentUser?.uid
    var userName by remember { mutableStateOf("") }
    var userEmail by remember { mutableStateOf("") }
    var userLocation by remember { mutableStateOf("") }

    // Referensi ke node "Users" di Realtime Database
    val database = FirebaseDatabase.getInstance()
    val usersRef = database.getReference("Users")

    LaunchedEffect(userId) {
        usersRef.child(userId.toString()) // Ganti dengan userId yang sesuai
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    // Ambil data pengguna dari snapshot
                    userEmail = snapshot.child("userEmail").getValue(String::class.java) ?: ""
                    userName = snapshot.child("userName").getValue(String::class.java) ?: ""
                    userLocation = snapshot.child("userlocation").getValue(String::class.java) ?: ""

                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, "Error fetching data", Toast.LENGTH_SHORT).show()
                }
            })
    }
    Scaffold(
        topBar = {
            androidx.compose.material.TopAppBar(
                title = { androidx.compose.material.Text("Profile") },
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
                    label = { androidx.compose.material.Text("Beranda") },
                    selected = true,
                    onClick = {
                        val intent = Intent(context, DaftarMakananPage::class.java)
                        context.startActivity(intent)
                    }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Search, null) },
                    label = { androidx.compose.material.Text("Cari") },
                    selected = false,
                    onClick = {
                        val intent = Intent(context, DaftarMakananPage::class.java)
                        context.startActivity(intent)
                    }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Favorite, null) },
                    label = { androidx.compose.material.Text("Disukai") },
                    selected = false,
                    onClick = {
                        val intent = Intent(context, DaftarFavorite::class.java)
                        context.startActivity(intent)
                    }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Person, null) },
                    label = { androidx.compose.material.Text("Profil") },
                    selected = false,
                    onClick = {

                    }
                )
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            // Header with profile photo and name
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Profile photo (kept as is)
                Icon(
                    Icons.Default.AccountCircle,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(100.dp),
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = userName,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Text(
                        text = userEmail,
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Stats row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("128 Resep", fontWeight = FontWeight.Bold)
                Text("1.2K Pengikut", fontWeight = FontWeight.Bold)
                Text("450 Mengikuti", fontWeight = FontWeight.Bold)
            }


            Spacer(modifier = Modifier.height(16.dp))

            Divider()

            Spacer(modifier = Modifier.height(16.dp))

            // Personal information section
            Column {
                Text(
                    text = "Informasi Pribadi",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Name field
                Column {
                    Text(
                        text = "Nama Lengkap",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = userName,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Email field
                Column {
                    Text(
                        text = "Email",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = userEmail,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Location field
                Column {
                    Text(
                        text = "Lokasi",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = userLocation,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Join date field
                Column {
                    Text(
                        text = "ID Users",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = userId.toString(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
