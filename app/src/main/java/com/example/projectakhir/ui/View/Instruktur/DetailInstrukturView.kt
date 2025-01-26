package com.example.projectakhir.ui.View.Instruktur

import com.example.projectakhir.ui.ViewModel.Instruktur.DetailInstrukturViewModel
import com.example.projectakhir.ui.navigasi.DestinasiNavigasi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectakhir.model.Instruktur
import com.example.projectakhir.ui.ViewModel.Instruktur.DetailInstruUiState
import com.example.projectakhir.ui.ViewModel.PenyediaViewModel
import com.example.projectakhir.ui.navigasi.CostumeTopAppBar


object DestinasiDetailInstruktur : DestinasiNavigasi {
    override val route = "detail"
    const val ID_Instru = "id_instru"
    val routeWithArg = "$route/{$ID_Instru}"
    override val titleRes = "Detail Mhs"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailInstrukturView(
    id_instruktur: Int,
    modifier: Modifier = Modifier,
    viewModel: DetailInstrukturViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onEditClick: (Int) -> Unit = {},
    navigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailInstruktur.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
                onRefresh = { viewModel.getDetailInstruktur() } // Trigger refresh action on refresh
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEditClick(id_instruktur) },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Mahasiswa"
                )
            }
        }
    ) { innerPadding ->
        // Menggunakan nama yang benar dari ViewModel
        val detailUiState by viewModel.detailIntrukturUiState.collectAsState()

        BodyDetailInstruktur(
            modifier = Modifier.padding(innerPadding),
            detailInstruUiState = detailUiState,
            retryAction = { viewModel.getDetailInstruktur() }
        )
    }
}

@Composable
fun BodyDetailInstruktur(
    modifier: Modifier = Modifier,
    detailInstruUiState: DetailInstruUiState,
    retryAction: () -> Unit = {}
) {
    when (detailInstruUiState) {
        is DetailInstruUiState.Loading -> { // Perbaikan referensi class sealed
            OnLoading(modifier = modifier.fillMaxSize())
        }
        is DetailInstruUiState.Success -> { // Perbaikan referensi class sealed
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ItemDetail(instruktur = detailInstruUiState.instruktur) // Properti Success
            }
        }
        is DetailInstruUiState.Error -> { // Perbaikan referensi class sealed
            OnError(
                retryAction = retryAction,
                modifier = modifier.fillMaxSize()
            )
        }
        else -> {
            Text("Unexpected state encountered")
        }
    }
}


@Composable
fun ItemDetail(
    instruktur: Instruktur
){
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            ComponentDetail(judul = "Nama Instruktur", isinya = instruktur.nama_instruktur)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetail(judul = "ID Instruktur", isinya = instruktur.id_instruktur.toString()) // Mengubah ke String
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetail(judul = "Email", isinya = instruktur.email)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetail(judul = "Deskripsi", isinya = instruktur.deskripsi)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetail(judul = "Nomor Telepon", isinya = instruktur.nomor_telepon)
        }
    }
}




@Composable
fun ComponentDetail(
    judul: String,
    isinya: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul :",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
    }
}
