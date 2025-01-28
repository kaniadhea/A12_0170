package com.example.projectakhir.ui

import android.util.Log
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projectakhir.R
import com.example.projectakhir.ui.View.Siswa.DestinasiHomeSiswa
import com.example.projectakhir.ui.navigasi.DestinasiNavigasi

// Tampilan awal aplikasi
object DestinasiHomePertama : DestinasiNavigasi {
    override val route = "home"
    override val titleRes : String
        get() = TODO("Not yet implemented")

}

@Composable
fun HomeAwalView(
    onKur: () -> Unit,
    onSwa: () -> Unit,
    onPen: () -> Unit,
    onIns: () -> Unit,
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
                .fillMaxWidth()
                .background(Color(0xFFF8A8B6))
                .padding(vertical = 29.dp, horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                onKur() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF8A8B6))
        ) {
            Text(text = "📘 Kursus", color = Color.White)
        }

        Spacer(modifier = Modifier.padding(2.dp))

        Button(
            onClick = { onPen() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF8A8B6))
        ) {
            Text(text = "📘 Pendaftaran", color = Color.White)
        }

        Spacer(modifier = Modifier.padding(2.dp))

        Button(
            onClick = { onIns() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF8A8B6))
        ) {
            Text(text = "🧑‍🏫 Instruktur", color = Color.White)
        }

        Spacer(modifier = Modifier.padding(2.dp))

        Button(
            onClick = {
                onSwa()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF8A8B6))
        ) {
            Text(text = "🧑‍🏫 Siswa", color = Color.White)
        }
    }
}
