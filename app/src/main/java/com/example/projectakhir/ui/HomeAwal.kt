package com.example.projectakhir.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectakhir.R


//tampilan awal aplikasi

object DestinasiHome : AlamatNavigasi{
    override val route = "home"
}

@Composable
fun HomeAwalView(
    onMk: () -> Unit,
    onDsn: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFAD1D1)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth() mmmmm
                .background(Color(0xFFF8A8B6))
                .padding(vertical = 29.dp, horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo with spacing
            Image(
                painter = painterResource(R.drawable.umy),
                contentDescription = "UMY Logo",
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "Selamat datang",
                color = Color(0xFF4B4B4B),
                style = TextStyle(
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = "Teknologi Informasi UMY",
                color = Color(0xFF4B4B4B),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Normal,
                ),
                modifier = Modifier.padding(top = 4.dp)
            )
        }


        Spacer(modifier = Modifier.padding(32.dp))


        Button(
            onClick = {
                onMk()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF8A8B6)
            )
        ) {
            Text(
                text = "📘 Kursus",
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.padding(2.dp))

        Button(
            onClick = {
                onDsn()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF8A8B6)
            )
        ) {
            Text(
                text = "🧑‍🏫 Instruktur",
                color = Color.White
            )
        }
    }
}