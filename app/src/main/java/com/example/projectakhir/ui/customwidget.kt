package com.example.projectakhir.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DropDownKursus(
    label: String,
    options: List<Int>, // Daftar opsi berupa Int
    selectedOption: Int?, // Nilai yang dipilih, null jika belum dipilih
    onOptionSelected: (Int) -> Unit // Callback untuk mengembalikan opsi terpilih
) {
    var isMenuOpen by remember { mutableStateOf(false) } // Mengganti nama dari expanded ke isMenuOpen

    Box(modifier = Modifier.padding(8.dp)) {
        // Input untuk menampilkan pilihan yang dipilih
        OutlinedTextField(
            value = selectedOption?.toString() ?: "", // Jika null, tampilkan string kosong
            onValueChange = {}, // Tidak memerlukan perubahan
            label = { Text(label) },
            readOnly = true, // Membuatnya hanya dapat diklik
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isMenuOpen = true } // Membuka menu dropdown
        )
        // Dropdown untuk menampilkan opsi
        DropdownMenu(
            expanded = isMenuOpen,
            onDismissRequest = { isMenuOpen = false } // Menutup menu saat klik di luar
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option.toString()) }, // Menampilkan opsi sebagai teks
                    onClick = {
                        onOptionSelected(option) // Mengembalikan nilai yang dipilih
                        isMenuOpen = false // Menutup menu setelah memilih
                    }
                )
            }
        }
    }
}