package com.example.projectakhir.ui.View.Siswa

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
import androidx.core.view.KeyEventDispatcher.Component
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectakhir.model.Siswa
import com.example.projectakhir.ui.ViewModel.PenyediaViewModel
import com.example.projectakhir.ui.ViewModel.Siswa.DetailSiswaUiState
import com.example.projectakhir.ui.ViewModel.Siswa.DetailSiswaViewModel
import com.example.projectakhir.ui.navigasi.CostumeTopAppBar
import com.example.projectakhir.ui.navigasi.DestinasiNavigasi

object DestinasiDetailSiswa : DestinasiNavigasi {
    override val route = "detail"
    const val ID_Siswa = "id_siswa"
    val routeWithArg = "$route/{$ID_Siswa}"
    override val titleRes = "Detail Siswa"
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailSiswaView(
    id_siswa: Int,  // Pastikan id_siswa bertipe Int
    modifier: Modifier = Modifier,
    viewModel: DetailSiswaViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onEditClick: (Int) -> Unit = {},  // Ubah tipe parameter menjadi Int
    navigateBack:()->Unit,
){
    println("Argumen id_siswa di DetailSiswaView: $id_siswa")
    val siswa = viewModel.detailUiState
    Scaffold(
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailSiswa.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
                onRefresh = { viewModel.getDetailSiswa() } // Trigger refresh action on refresh
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEditClick(id_siswa) },  // id_siswa dikirimkan sebagai Int
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Siswa"
                )
            }
        }
    ) { innerPadding ->
        val detailUiState by viewModel.detailUiState.collectAsState()

        BodyDetail(
            modifier = Modifier.padding(innerPadding),
            detailSiswaUiState = detailUiState,
            retryAction = { viewModel.getDetailSiswa() }
        )
    }
}



@Composable
fun BodyDetail(
    modifier: Modifier = Modifier,
    detailSiswaUiState: DetailSiswaUiState,
    retryAction: () -> Unit = {}
) {
    when (detailSiswaUiState) {
        is DetailSiswaUiState.Loading -> {
            Text("Loading data...", modifier = modifier)
        }
        is DetailSiswaUiState.Success -> {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ItemDetailSiswa(siswa = detailSiswaUiState.siswa)
            }
        }
        is DetailSiswaUiState.Error -> {
            Text("Error loading data. Silakan coba lagi.", modifier = modifier)
        }
    }
}


@Composable
fun ItemDetailSiswa(
    siswa: Siswa
){
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            ComponentDetail(judul = "ID Siswa", isinya = siswa.id_siswa.toString())  // Mengubah id_siswa ke String
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetail(judul = "Nama", isinya = siswa.nama_siswa)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetail(judul = "Email", isinya = siswa.email)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetail(judul = "Nomor Telepon", isinya = siswa.nomor_telepon)
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
