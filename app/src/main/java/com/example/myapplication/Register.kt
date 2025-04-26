package com.example.myapplication


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.Image
import androidx.compose.foundation.border

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.Icon

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.models.AuthModel


class Register : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewmodel = ViewModelProvider(this)[AuthModel::class.java]

        setContent {
            RegisterPage(authModel = viewmodel)
        }
    }
}


@Composable
fun RegisterPage (authModel: AuthModel) {

    var email by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var konfirmasiPassword by remember { mutableStateOf("") }
    var lokasi by remember { mutableStateOf("") }
    val context = LocalContext.current




    Box(modifier = Modifier.fillMaxSize()) {
        // Background dengan efek blur
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background login",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .blur(radius = 20.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = "Logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape)
            )

            Text(
                text = "Resep Kita",
                fontSize = 24.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 16.dp)
            )

            Text(
                text = "Mulai perjalanan kuliner mu",
                fontSize = 18.sp,
                color = Color.hsl(20f, 1f, 0.4f)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                // EMAIL
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Masukkan nama") },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFFFA726),
                        focusedTextColor = Color.Black,
                        focusedPlaceholderColor = Color(0xFFFFA726)
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))
                // EMAIL
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Alamat Email") },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Email, contentDescription = null)
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFFFA726),
                        focusedTextColor = Color.Black,
                        focusedPlaceholderColor = Color(0xFFFFA726)
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                // PASSWORD
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Kata Sandi") },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Lock, contentDescription = null)
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFFFA726),
                        focusedTextColor = Color.Black,
                        focusedPlaceholderColor = Color(0xFFFFA726)
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))

                //konfirmasi password
                OutlinedTextField(
                    value = konfirmasiPassword,
                    onValueChange = { konfirmasiPassword = it },
                    label = { Text("Konfirmasi Kata Sandi") },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Lock, contentDescription = null)
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFFFA726),
                        focusedTextColor = Color.Black,
                        focusedPlaceholderColor = Color(0xFFFFA726)
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                //lokasi
                OutlinedTextField(
                    value = lokasi,
                    onValueChange = { lokasi = it },
                    label = { Text("Lokasi anda (Kota)") },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFFFA726),
                        focusedTextColor = Color.Black,
                        focusedPlaceholderColor = Color(0xFFFFA726)
                    )
                )
                Spacer(modifier = Modifier.height(24.dp))
                // Daftar Button
                Button(
                    onClick = {
                        authModel.signUp(email,name,password,konfirmasiPassword,lokasi){sukses, pesan ->
                            if (sukses){
                                val intent = Intent(context, MainActivity::class.java)
                                context.startActivity(intent)
                                Toast.makeText(context, pesan, Toast.LENGTH_SHORT).show()
                            }else{
                                Toast.makeText(context, pesan, Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6F00)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Daftar", color = Color.White)
                }

            }
        }
    }
}




