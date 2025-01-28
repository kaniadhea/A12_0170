package com.example.projectakhir.ui.View.Pendaftaran


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
import com.example.projectakhir.ui.ViewModel.Pendaftaran.InsertPendaftaranUiState
import com.example.projectakhir.ui.ViewModel.Pendaftaran.InsertPendaftaranViewModel
import com.example.projectakhir.ui.ViewModel.Pendaftaran.InsertPendaftranUiEvent
import com.example.projectakhir.ui.ViewModel.PenyediaViewModel
import com.example.projectakhir.ui.navigasi.CostumeTopAppBar
import com.example.projectakhir.ui.navigasi.DestinasiNavigasi
import kotlinx.coroutines.launch


object DestinasiEntryPndftrn : DestinasiNavigasi {
    override val route = "item_entry_pendaftaran"
    override val titleRes = "Tambah Data Pendaftaran"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryPndftrnScreen(
    navigateBack:()->Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertPendaftaranViewModel = viewModel(factory = PenyediaViewModel.Factory)

){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiEntryPndftrn.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryBodyPndftrn(
            insertPendaftaranUiState = viewModel.uiState,
            onSiswaValueChange = viewModel::updateInsertPndftrnState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertPndftrn()
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
fun EntryBodyPndftrn(
    insertPendaftaranUiState: InsertPendaftaranUiState,
    onSiswaValueChange:(InsertPendaftranUiEvent)->Unit,
    onSaveClick:()-> Unit,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputPndftrn(
            insertUiEvent = insertPendaftaranUiState.insertPendftaranUiEvent,
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
fun FormInputPndftrn(
    insertUiEvent: InsertPendaftranUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertPendaftranUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // ID Pendaftaran
        OutlinedTextField(
            value = insertUiEvent.id_pendaftaran.toString(), // Convert Int to String
            onValueChange = {
                val idPendaftaran = it.toIntOrNull() ?: 0 // Convert back to Int
                onValueChange(insertUiEvent.copy(id_pendaftaran = idPendaftaran))
            },
            label = { Text("ID_pendaftaran") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        // ID Siswa
        OutlinedTextField(
            value = insertUiEvent.id_siswa.toString(), // Convert Int to String
            onValueChange = {
                val idSiswa = it.toIntOrNull() ?: 0 // Convert back to Int
                onValueChange(insertUiEvent.copy(id_siswa = idSiswa))
            },
            label = { Text("ID_Siswa") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        // ID Kursus
        OutlinedTextField(
            value = insertUiEvent.id_kursus.toString(), // Convert Int to String
            onValueChange = {
                val idKursus = it.toIntOrNull() ?: 0 // Convert back to Int
                onValueChange(insertUiEvent.copy(id_kursus = idKursus))
            },
            label = { Text("ID_Kursus") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        // Tanggal Pendaftaran
        OutlinedTextField(
            value = insertUiEvent.tanggal_pendaftaran,
            onValueChange = { onValueChange(insertUiEvent.copy(tanggal_pendaftaran = it)) },
            label = { Text("Alamat") },
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


