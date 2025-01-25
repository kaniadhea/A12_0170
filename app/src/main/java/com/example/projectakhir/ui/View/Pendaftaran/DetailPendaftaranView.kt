package com.example.projectakhir.ui.View.Pendaftaran

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
import com.example.pam11.ui.ViewModel.DetailPendaftaranUiState
import com.example.pam11.ui.ViewModel.DetailPendaftaranViewModel
import com.example.projectakhir.model.Pendaftaran
import com.example.projectakhir.ui.ViewModel.PenyediaViewModel
import com.example.projectakhir.ui.navigasi.CostumeTopAppBar
import com.example.projectakhir.ui.navigasi.DestinasiNavigasi


object DestinasiDetailPendaftran : DestinasiNavigasi {
    override val route = "detail"
    const val ID_Pendaftaran = "id_pendaftaran"
    val routeWithArg = "$route/{$ID_Pendaftaran}"
    override val titleRes = "Detail Pndftrn"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPendaftaranView(
    id_pendaftaran: String,
    modifier: Modifier = Modifier,
    viewModel: DetailPendaftaranViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onEditClick: (String) -> Unit = {},
    navigateBack:()->Unit,
){
    Scaffold(
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailPendaftran.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
                onRefresh = { viewModel.getDetailPndftrn() } // Trigger refresh action on refresh
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEditClick(id_pendaftaran) },
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
        val detailUiState by viewModel.detailPndftrnUiState.collectAsState()

        BodyDetail(
            modifier = Modifier.padding(innerPadding),
            detailPendaftaranUiState = detailUiState,
            retryAction = { viewModel.getDetailPndftrn() }
        )
    }
}


@Composable
fun BodyDetail(
    modifier: Modifier = Modifier,
    detailPendaftaranUiState: DetailPendaftaranUiState,
    retryAction: () -> Unit = {}
) {
    when (detailPendaftaranUiState) {
        is DetailPendaftaranUiState.Loading -> {
            OnLoading(modifier = modifier.fillMaxSize())
        }
        is DetailPendaftaranUiState.Success -> {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ItemDetail(pendaftaran = detailPendaftaranUiState.pendaftaran)
            }
        }
        is DetailPendaftaranUiState.Error -> {
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
    pendaftaran: Pendaftaran
){
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            ComponentDetail(judul = "ID_pendaftaran", isinya = pendaftaran.id_pendaftaran)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetail(judul = "ID_Siswa", isinya = pendaftaran.id_siswa)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetail(judul = "ID_Kursus", isinya = pendaftaran.id_kursus)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetail(judul = "Tanggal Pendaftaran", isinya = pendaftaran.tanggal_pendaftaran)
            Spacer(modifier = Modifier.padding(4.dp))
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
