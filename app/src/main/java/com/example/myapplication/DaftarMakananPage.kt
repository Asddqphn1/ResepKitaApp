package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

import com.example.myapplication.models.DaftarMakan
import com.example.myapplication.viewmodels.DaftarMakananView
import com.example.myapplication.viewmodels.FilterMakananView

class DaftarMakananPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ResepKitaApp()
        }
    }
}

@Composable
fun ResepKitaApp(
    viewModel: DaftarMakananView = viewModel(),
    filterViewModel: FilterMakananView = viewModel()
) {
    val categories by viewModel.category.collectAsState()
    val makanan by filterViewModel.filterMakaan.collectAsState()
    val safeMakanan = makanan ?: emptyList()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("🍽️ Resep Kita") }, backgroundColor = Color(0xFFFFA726))
        },
        bottomBar = {
            BottomNavigation (backgroundColor = Color(0xFFFFA726)) {
                BottomNavigationItem(icon = { Icon(Icons.Default.Home, null) }, label = { Text("Beranda") }, selected = true, onClick = {})
                BottomNavigationItem(icon = { Icon(Icons.Default.Search, null) }, label = { Text("Cari") }, selected = false, onClick = {})
                BottomNavigationItem(icon = { Icon(Icons.Default.Favorite, null) }, label = { Text("Disukai") }, selected = false, onClick = {})
                BottomNavigationItem(icon = { Icon(Icons.Default.Person, null) }, label = { Text("Profil") }, selected = false, onClick = {})
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .padding(16.dp)
        ) {
            // Search bar
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Cari resep...") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Filter kategori
            LazyRow {
                items(categories) { item ->
                    Button(
                        onClick = { filterViewModel.fetchmakananfilter(item.kategori ?: "Beef") },
                        modifier = Modifier.padding(end = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFA726),
                            contentColor = Color.White
                        )
                    ) {
                        Text(item.kategori ?: "Tanpa Nama")
                    }
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            // Section rekomendasi
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            ) {
                // Nanti diganti gambar
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray)
                ) {
                    Image(
                        painterResource(id = R.drawable.makanan),
                        contentDescription = "Logo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Text(
                        "Resep Populer Minggu Ini\nCoba masakan baru setiap hari",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Grid daftar resep
            Text("Daftar Resep", fontWeight = FontWeight.Bold, fontSize = 18.sp)

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxHeight()
            ) {
                items(safeMakanan){item ->
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            // Gambar placeholder
                            AsyncImage(
                                model = item.thumbniail,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(100.dp)
                                    .fillMaxWidth()
                                    .background(Color.Gray)
                            )


                            Spacer(modifier = Modifier.height(8.dp))

                            Text(item.namaMakanan ?: "Tanpa Nama", fontWeight = FontWeight.SemiBold)
                            Row {
                                Icon(
                                    Icons.Default.Star,
                                    contentDescription = null,
                                    tint = Color.Yellow,
                                    modifier = Modifier.size(16.dp)
                                )
                                Text("4.5", color = Color.Gray)
                            }
                        }
                        }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview(){
    ResepKitaApp()
}
