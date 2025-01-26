package com.example.projectakhir.ui.View.Pendaftaran
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectakhir.R
import com.example.projectakhir.model.Pendaftaran
import com.example.projectakhir.ui.View.Instruktur.DestinasiHomeInstruktur
import com.example.projectakhir.ui.ViewModel.Pendaftaran.HomePendaftaranUiState
import com.example.projectakhir.ui.ViewModel.Pendaftaran.HomePendaftaranViewModel

import com.example.projectakhir.ui.ViewModel.PenyediaViewModel
import com.example.projectakhir.ui.navigasi.CostumeTopAppBar
import com.example.projectakhir.ui.navigasi.DestinasiNavigasi


object DestinasiHomePendaftaran: DestinasiNavigasi {
    override val route ="home"
    override val titleRes = "Home Mhs"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePendftaranScreen(
    navigateTolItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit = {},  // Ganti parameter jadi Int
    viewModel: HomePendaftaranViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiHomePendaftaran.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getpndftrn()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton (
                onClick = navigateTolItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Mahasiswa")
            }
        },
    ) { innerPadding ->
        HomePndftrnStatus(
            homePendaftaranUiState = viewModel.pdftnUiState,
            retryAction = { viewModel.getpndftrn() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,  // onDetailClick menerima Int
            onDeleteClick = {
                viewModel.deletePndftrn(it.id_pendaftaran)
                viewModel.getpndftrn()
            }
        )
    }
}



@Composable
fun HomePndftrnStatus(
    homePendaftaranUiState: HomePendaftaranUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Pendaftaran) -> Unit = {},
    onDetailClick: (Int) -> Unit
){
    when (homePendaftaranUiState){
        is HomePendaftaranUiState.Loading-> OnLoading(modifier = modifier.fillMaxSize())


        is HomePendaftaranUiState.Success ->
            if(homePendaftaranUiState.pendaftaran.isEmpty()){
                return Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Text(text = "Tidak ada data kontak")
                }
            }else{
                PndftrnLayout(
                    pendaftaran = homePendaftaranUiState.pendaftaran,modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.id_pendaftaran)
                    },
                    onDeleteClick={
                        onDeleteClick(it)
                    }
                )
            }
        is HomePendaftaranUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}



@Composable
fun OnLoading(modifier: Modifier = Modifier){
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.gambarloading),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun OnError(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }

    }
}

@Composable
fun PndftrnLayout(
    pendaftaran: List<Pendaftaran>,
    modifier: Modifier = Modifier,
    onDetailClick:(Pendaftaran) -> Unit,
    onDeleteClick: (Pendaftaran) -> Unit = {}
){
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(pendaftaran){ pendaftaran ->
            PndftrnCard(
                pendaftaran = pendaftaran,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(pendaftaran) },
                onDeleteClick ={
                    onDeleteClick(pendaftaran)
                }
            )


        }

    }
}

@Composable
fun PndftrnCard(
    pendaftaran: Pendaftaran,
    modifier: Modifier = Modifier,
    onDeleteClick: (Pendaftaran) -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Convert id_pendaftaran, id_siswa, id_kursus from Int to String
                Text(
                    text = pendaftaran.id_pendaftaran.toString(),  // Convert to String
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onDeleteClick(pendaftaran) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                    )
                }

                Text(
                    text = pendaftaran.id_siswa.toString(),  // Convert to String
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Text(
                text = pendaftaran.id_kursus.toString(),  // Convert to String
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = pendaftaran.tanggal_pendaftaran,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
