package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

import com.example.myapplication.viewmodels.DetailMakananView


class DetailMakanan : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.navigationBars())
        controller.hide(WindowInsetsCompat.Type.statusBars())
        val id = intent.getStringExtra("id")
        val viewmodel = ViewModelProvider(this)[DetailMakananView::class.java]
        Log.e("id", id.toString())
        enableEdgeToEdge()
        setContent {
            Detail(viewmodel, id.toString())
        }
    }
}

@Composable
fun Detail(detailMakananView: DetailMakananView = viewModel(), idmakanan : String){

    detailMakananView.fetchDetailMakana(idmakanan)
    val detail by detailMakananView.detail.collectAsState()
    val context = LocalContext.current






    Scaffold(
        topBar = {
            TopAppBar(
                title = { androidx.compose.material.Text("Resep Favorite") },
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
        }
    ) { paddingValues ->

        LazyColumn (modifier = Modifier
            .padding(paddingValues)
            .padding(20.dp)){
            items(detail){item ->
                Column {
                    AsyncImage(
                        model = item.gambarMakanan,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(100.dp)
                            .fillMaxWidth()
                            .background(Color.Gray)
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(text = item.namaMakanan.toString(), fontWeight = FontWeight.Bold, fontSize = 17.sp)
                    Row {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = null,
                            tint = Color.Yellow,
                            modifier = Modifier.size(16.dp)
                        )
                        Text("4.5", color = Color.Gray)
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = item.categoryMakanan.toString(), fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text("INGREDIENT")
                    Spacer(modifier = Modifier.height(15.dp))
                    // Menampilkan teks hanya jika item.gradientX tidak null atau tidak kosong
                    if (!item.gradient1.isNullOrEmpty()) {
                        Text(text = item.gradient1 + " ${item.jumlah1}")
                    }
                    if (!item.gradient2.isNullOrEmpty()) {
                        Text(text = item.gradient2 + " ${item.jumlah2}")
                    }
                    if (!item.gradient3.isNullOrEmpty()) {
                        Text(text = item.gradient3 + " ${item.jumlah3}")
                    }
                    if (!item.gradient4.isNullOrEmpty()) {
                        Text(text = item.gradient4 + " ${item.jumlah4}")
                    }
                    if (!item.gradient5.isNullOrEmpty()) {
                        Text(text = item.gradient5 + " ${item.jumlah5}")
                    }
                    if (!item.gradient6.isNullOrEmpty()) {
                        Text(text = item.gradient6 + " ${item.jumlah6}")
                    }
                    if (!item.gradient7.isNullOrEmpty()) {
                        Text(text = item.gradient7 + " ${item.jumlah7}")
                    }
                    if (!item.gradient8.isNullOrEmpty()) {
                        Text(text = item.gradient8 + " ${item.jumlah8}")
                    }
                    if (!item.gradient9.isNullOrEmpty()) {
                        Text(text = item.gradient9 + " ${item.jumlah9}")
                    }
                    if (!item.gradient10.isNullOrEmpty()) {
                        Text(text = item.gradient10 + " ${item.jumlah10}")
                    }
                    if (!item.gradient11.isNullOrEmpty()) {
                        Text(text = item.gradient11 + " ${item.jumlah11}")
                    }
                    if (!item.gradient12.isNullOrEmpty()) {
                        Text(text = item.gradient12 + " ${item.jumlah12}")
                    }
                    if (!item.gradient13.isNullOrEmpty()) {
                        Text(text = item.gradient13 + " ${item.jumlah13}")
                    }
                    if (!item.gradient14.isNullOrEmpty()) {
                        Text(text = item.gradient14 + " ${item.jumlah14}")
                    }
                    if (!item.gradient15.isNullOrEmpty()) {
                        Text(text = item.gradient15 + " ${item.jumlah15}")
                    }
                    if (!item.gradient16.isNullOrEmpty()) {
                        Text(text = item.gradient16 + " ${item.jumlah16}")
                    }
                    if (!item.gradient17.isNullOrEmpty()) {
                        Text(text = item.gradient17 + " ${item.jumlah17}")
                    }
                    if (!item.gradient18.isNullOrEmpty()) {
                        Text(text = item.gradient18 + " ${item.jumlah18}")
                    }
                    if (!item.gradient19.isNullOrEmpty()) {
                        Text(text = item.gradient19 + " ${item.jumlah19}")
                    }
                    if (!item.gradient20.isNullOrEmpty()) {
                        Text(text = item.gradient20 + " ${item.jumlah20}")
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(text = item.perintah.toString())

                    Spacer(modifier = Modifier.height(15.dp))

                    Button(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.linkYoutube))
                            context.startActivity(intent)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCD1C1C)), // Red YouTube color
                        shape = MaterialTheme.shapes.medium,
                        contentPadding = PaddingValues(start = 16.dp, end = 16.dp)
                    ) {
                        // YouTube Icon and Text
                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            // YouTube Icon
                            Icon(
                                painter = painterResource(id = R.drawable.youtube_brands), // Use a YouTube icon resource
                                contentDescription = "YouTube Icon",
                                modifier = Modifier.size(24.dp),
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            // Button Text
                            Text(
                                text = "Tonton di YouTube",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                }
            }
        }
    }
}