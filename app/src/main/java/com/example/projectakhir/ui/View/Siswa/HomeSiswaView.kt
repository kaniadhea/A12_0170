package com.example.projectakhir.ui.View.Siswa

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
import com.example.projectakhir.model.Siswa
import com.example.projectakhir.ui.ViewModel.PenyediaViewModel
import com.example.projectakhir.ui.ViewModel.Siswa.HomeSiswaUiState
import com.example.projectakhir.ui.ViewModel.Siswa.HomeSiswaViewModel
import com.example.projectakhir.ui.navigasi.CostumeTopAppBar
import com.example.projectakhir.ui.navigasi.DestinasiNavigasi


object DestinasiHomeSiswa: DestinasiNavigasi {
    override val route ="home"
    override val titleRes = "Home Mhs"
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenSiswa(
    navigateTolItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomeSiswaViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiHomeSiswa.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getSwa()
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
    ) { innerPadding->
        HomeStatusSiswa(
            homeSiswaUiState = viewModel.swaUiState,
            retryAction = {viewModel.getSwa()}, modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,onDeleteClick = {
                viewModel.deleteSwa(it.id_siswa)
                viewModel.getSwa()
            }
        )
    }

}


@Composable
fun HomeStatusSiswa(
    homeSiswaUiState: HomeSiswaUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Siswa) -> Unit = {},
    onDetailClick: (String) -> Unit
){
    when (homeSiswaUiState){
        is HomeSiswaUiState.Loading-> OnLoading(modifier = modifier.fillMaxSize())


        is HomeSiswaUiState.Success -> {
            if(homeSiswaUiState.siswa.isEmpty()){
                return Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Text(text = "Tidak ada data kontak")
                }
            } else {
                SwaLayout(
                    siswa = homeSiswaUiState.siswa,modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.id_siswa.toString()) // Convert id_siswa to String
                    },
                    onDeleteClick={
                        onDeleteClick(it)
                    }
                )
            }
        }
        is HomeSiswaUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
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
fun SwaLayout(
    siswa: List<Siswa>,
    modifier: Modifier = Modifier,
    onDetailClick:(Siswa) -> Unit,
    onDeleteClick: (Siswa) -> Unit = {}
){
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(siswa){ siswa ->
            SwaCard(
                siswa = siswa,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(siswa) },
                onDeleteClick ={
                    onDeleteClick(siswa)
                }
            )
        }

    }
}

@Composable
fun SwaCard(
    siswa: Siswa,
    modifier: Modifier = Modifier,
    onDeleteClick:(Siswa)-> Unit = {}
){
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
                Text(
                    text = siswa.nama_siswa,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = {onDeleteClick(siswa)}) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                    )
                }


                Text(
                    text = siswa.id_siswa.toString(), // Convert id_siswa to String
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Text(
                text = siswa.email,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = siswa.nomor_telepon,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
