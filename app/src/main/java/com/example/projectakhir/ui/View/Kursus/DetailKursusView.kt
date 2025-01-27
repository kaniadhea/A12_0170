package com.example.projectakhir.ui.View.Kursus

import com.example.projectakhir.ui.ViewModel.PenyediaViewModel
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
import com.example.projectakhir.model.Kursus
import com.example.projectakhir.ui.ViewModel.Kursus.DetailKursusUiState
import com.example.projectakhir.ui.ViewModel.Kursus.DetailKursusViewModel
import com.example.projectakhir.ui.navigasi.CostumeTopAppBar

object DestinasiDetailKursus : DestinasiNavigasi {
    override val route = "detail"
    const val ID_Kursus = "id"
    val routeWithArg = "$route/{$ID_Kursus}"
    override val titleRes = "Detail Mhs"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailKursusView(
    id_kursus: Int,  // Change here
    modifier: Modifier = Modifier,
    viewModel: DetailKursusViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onEditClick: (Int) -> Unit = {},  // Change here
    navigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailKursus.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
                onRefresh = { viewModel.getDetailKursus() } // Trigger refresh action on refresh
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEditClick(id_kursus) },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Kursus"
                )
            }
        }
    ) { innerPadding ->
        val detailUiState by viewModel.detailKursusUiState.collectAsState()

        BodyDetail(
            modifier = Modifier.padding(innerPadding),
            detailKursusUiState = detailUiState,
            retryAction = { viewModel.getDetailKursus() }
        )
    }
}

@Composable
fun BodyDetail(
    modifier: Modifier = Modifier,
    detailKursusUiState: DetailKursusUiState,
    retryAction: () -> Unit = {}
) {
    when (detailKursusUiState) {
        is DetailKursusUiState.Loading -> {
            OnLoading(modifier = modifier.fillMaxSize())
        }
        is DetailKursusUiState.Success -> {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ItemDetail(kursus = detailKursusUiState.kursus)
            }
        }
        is DetailKursusUiState.Error -> {
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
    kursus: Kursus
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            ComponentDetail(judul = "Id Kursus", isinya = kursus.id_kursus.toString()) // Convert Int to String
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetail(judul = "Nama", isinya = kursus.nama_kursus)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetail(judul = "Harga", isinya = kursus.harga)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetail(judul = "Deskripsi", isinya = kursus.deskripsi)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetail(judul = "Kategori", isinya = kursus.kategori)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetail(judul = "Id Instruktur", isinya = kursus.id_instruktur.toString()) // Convert Int to String
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
