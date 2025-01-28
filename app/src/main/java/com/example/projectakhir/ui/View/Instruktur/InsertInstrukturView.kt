package com.example.projectakhir.ui.View.Instruktur

import android.util.Log
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectakhir.ui.ViewModel.Instruktur.InsertInstrukturUiEvent
import com.example.projectakhir.ui.ViewModel.Instruktur.InsertInstrukturUiState
import com.example.projectakhir.ui.ViewModel.Instruktur.InsertInstrukturViewModel
import com.example.projectakhir.ui.ViewModel.PenyediaViewModel
import com.example.projectakhir.ui.navigasi.CostumeTopAppBar
import com.example.projectakhir.ui.navigasi.DestinasiNavigasi
import kotlinx.coroutines.launch


object DestinasiEntryInstruktur : DestinasiNavigasi{
    override val route = "item_entry_instruktur"
    override val titleRes = "Tambah Data Instruktur"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryInstruScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertInstrukturViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
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
        EntryBodyIntruktur(
            insertInstrukturUiState = viewModel.instruuiState,
            onInstruValueChange = viewModel::updateInsertInstruState,
            onSaveClick = {
                if (viewModel.instruuiState.insertInstrukturUiEvent.isValid()) {
                    // Log data before saving
                    Log.d("InsertInstruktur", "Saving data: ${viewModel.instruuiState.insertInstrukturUiEvent}")
                    coroutineScope.launch {
                        viewModel.insertInstru()
                        navigateBack()
                    }
                } else {
                    Log.e("InsertInstruktur", "Form is not valid!")
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
fun EntryBodyIntruktur(
    insertInstrukturUiState: InsertInstrukturUiState,
    onInstruValueChange: (InsertInstrukturUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
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
            modifier = Modifier.fillMaxWidth(),
            enabled = insertInstrukturUiState.insertInstrukturUiEvent.isValid() // Only enable if the form is valid
        ) {
            Text(text = "Simpan")
        }
    }
}


@Composable
fun FormInput(
    insertInstrukturUiEvent: InsertInstrukturUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertInstrukturUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
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
        OutlinedTextField(
            value = insertInstrukturUiEvent.email,
            onValueChange = { onValueChange(insertInstrukturUiEvent.copy(email = it)) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
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
            value = insertInstrukturUiEvent.nomor_telepon,
            onValueChange = { onValueChange(insertInstrukturUiEvent.copy(nomor_telepon = it)) },
            label = { Text("Nomor Telepon") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(12.dp)
        )
    }
}


// Add validation logic for the UI state
fun InsertInstrukturUiEvent.isValid(): Boolean {
    return nama_instruktur.isNotBlank() && email.isNotBlank() && email.contains("@") && nomor_telepon.isNotBlank()
}






