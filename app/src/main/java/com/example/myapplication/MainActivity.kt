package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.models.AuthModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewmodel = ViewModelProvider(this)[AuthModel::class.java]
            MyApplicationTheme {
                Greeting(viewmodel)
            }
        }
    }
}

@Composable
fun Greeting(authModel: AuthModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
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
                text = "Masak hal baru setiap hari",
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

                // MASUK Button
                Button(
                    onClick = {
                        authModel.login(email,password){sukses, pesan ->
                            if (sukses){
                                val intent = Intent(context, DaftarMakananPage::class.java)
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
                    Text("Masuk", color = Color.White)
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Lupa Kata Sandi
                Text(
                    text = "Lupa Kata Sandi?",
                    color = Color(0xFFFF6F00),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.clickable { /* TODO */ }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Divider "atau"
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Divider(modifier = Modifier.weight(1f))
                    Text(
                        "  atau  ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                    Divider(modifier = Modifier.weight(1f))
                }

                Spacer(modifier = Modifier.height(16.dp))

                // DAFTAR Button
                OutlinedButton(
                    onClick = {
                        val intent = Intent(context, Register::class.java)
                        context.startActivity(intent)
                    },
                    border = ButtonDefaults.outlinedButtonBorder.copy(width = 1.dp, brush = androidx.compose.ui.graphics.SolidColor(Color(0xFFFF6F00))),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text(
                        "Daftar Akun Baru",
                        color = Color(0xFFFF6F00)
                    )
                }
            }
        }
    }
}

