package com.example.projectakhir.ui.View.Instruktur

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectakhir.ui.ViewModel.Instruktur.InsertInstrukturUiEvent
import com.example.projectakhir.ui.ViewModel.Instruktur.InsertInstrukturUiState
import com.example.projectakhir.ui.ViewModel.Instruktur.InsertInstrukturViewModel
import com.example.projectakhir.ui.ViewModel.PenyediaViewModel
import com.example.projectakhir.ui.navigasi.CostumeTopAppBar
import com.example.projectakhir.ui.navigasi.DestinasiNavigasi

import kotlinx.coroutines.launch


object DestinasiEntryInstruktur : DestinasiNavigasi {
    override val route = "item_entry"
    override val titleRes = "Entry Instru"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryInstruScreen(
    navigateBack:()->Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertInstrukturViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiEntryInstruktur.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryBodyInstru(
            insertInstrukturUiState = viewModel.instruUiState,
            onInstruValueChange = viewModel::updateInsertInstruState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertInstru()
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
fun EntryBodyInstru(
    insertInstrukturUiState: InsertInstrukturUiState,
    onInstruValueChange:(InsertInstrukturUiEvent)->Unit,
    onSaveClick:()-> Unit,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInput(
            insertInstrukturUiEvent = insertInstrukturUiState.insertInstrukturUiEvent,
            onValueChange = onInstruValueChange,
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
    insertInstrukturUiEvent: InsertInstrukturUiEvent,
    modifier: Modifier = Modifier,
    onValueChange:(InsertInstrukturUiEvent)->Unit = {},
    enabled: Boolean = true
){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertInstrukturUiEvent.nama_instruktur,
            onValueChange = { onValueChange(insertInstrukturUiEvent.copy(nama_instruktur = it)) },
            label = { Text("Nama") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        // Menggunakan KeyboardType.Number dan mengonversi nilai ke Int
        OutlinedTextField(
            value = insertInstrukturUiEvent.id_instruktur.toString(),
            onValueChange = {
                val newId = it.toIntOrNull() ?: 0
                onValueChange(insertInstrukturUiEvent.copy(id_instruktur = newId))
            },
            label = { Text("ID Instruktur") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = insertInstrukturUiEvent.deskripsi,
            onValueChange = { onValueChange(insertInstrukturUiEvent.copy(deskripsi = it)) },
            label = { Text("Deskripsi") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = insertInstrukturUiEvent.email,
            onValueChange = { onValueChange(insertInstrukturUiEvent.copy(email = it)) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = insertInstrukturUiEvent.nomor_telepon,
            onValueChange = { onValueChange(insertInstrukturUiEvent.copy(nomor_telepon = it)) },
            label = { Text("Kelas") },
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

