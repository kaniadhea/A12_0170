package com.example.projectakhir.ui.View.Kursus

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectakhir.ui.ViewModel.PenyediaViewModel
import com.example.projectakhir.ui.ViewModel.Kursus.InsertKursusUiEvent
import com.example.projectakhir.ui.ViewModel.Kursus.InsertKursusUiState
import com.example.projectakhir.ui.ViewModel.Kursus.InsertKursusViewModel
import com.example.projectakhir.ui.navigasi.CostumeTopAppBar
import com.example.projectakhir.ui.navigasi.DestinasiNavigasi
import kotlinx.coroutines.launch


object DestinasiEntryKursus : DestinasiNavigasi {
    override val route = "item_entry_kursus"
    override val titleRes = "Tambah Data Kursus"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryKurScreen(
    navigateBack:()->Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertKursusViewModel = viewModel(factory = PenyediaViewModel.Factory)

){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiEntryKursus.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryBodyKursus(
            insertKursusUiState = viewModel.kursUiState,
            onSiswaValueChange = viewModel::updateInsertKursState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertKurs()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun EntryBodyKursus(
    insertKursusUiState: InsertKursusUiState,
    onSiswaValueChange:(InsertKursusUiEvent)->Unit,
    onSaveClick:()-> Unit,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInput(
            insertKursusUiEvent = insertKursusUiState.insertKursusUiEvent,
            onValueChange = onSiswaValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan")
        }
    }

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(
    insertKursusUiEvent: InsertKursusUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertKursusUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertKursusUiEvent.nama_kursus,
            onValueChange = { onValueChange(insertKursusUiEvent.copy(nama_kursus = it)) },
            label = { Text("Nama") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertKursusUiEvent.id_kursus.toString(), // Konversi Int ke String
            onValueChange = {
                val newValue = it.toIntOrNull() ?: 0 // Konversi kembali ke Int, default 0 jika kosong
                onValueChange(insertKursusUiEvent.copy(id_kursus = newValue))
            },
            label = { Text("Id Kursus") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertKursusUiEvent.harga,
            onValueChange = { onValueChange(insertKursusUiEvent.copy(harga = it)) },
            label = { Text("Harga") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertKursusUiEvent.deskripsi,
            onValueChange = { onValueChange(insertKursusUiEvent.copy(deskripsi = it)) },
            label = { Text("Deskripsi") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertKursusUiEvent.kategori,
            onValueChange = { onValueChange(insertKursusUiEvent.copy(kategori = it)) },
            label = { Text("Kategori") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertKursusUiEvent.id_instruktur.toString(), // Konversi Int ke String
            onValueChange = {
                val newValue = it.toIntOrNull() ?: 0 // Konversi kembali ke Int, default 0 jika kosong
                onValueChange(insertKursusUiEvent.copy(id_instruktur = newValue))
            },
            label = { Text("Id Instruktur") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if (enabled) {
            Text(
                text = "Isi Semua Data!",
                modifier = Modifier.padding(12.dp)
            )
        }

        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(12.dp)
        )
    }
}
