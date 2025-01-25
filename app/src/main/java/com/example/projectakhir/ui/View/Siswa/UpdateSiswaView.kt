package com.example.projectakhir.ui.View.Siswa

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectakhir.ui.ViewModel.PenyediaViewModel
import com.example.projectakhir.ui.ViewModel.Siswa.UpdateSiswaViewModel
import com.example.projectakhir.ui.ViewModel.Siswa.toSwa
import com.example.projectakhir.ui.navigasi.CostumeTopAppBar
import com.example.projectakhir.ui.navigasi.DestinasiNavigasi
import kotlinx.coroutines.launch

object DestinasiUpdateSiswa : DestinasiNavigasi {
    override val route = "update"
    const val ID_Siswa = "nim"
    val routesWithArg = "$route/{$ID_Siswa}"
    override val titleRes = "Update Mhs"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateSiswaView(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateSiswaViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val uiState = viewModel.uiState.value

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiUpdateSiswa.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ){ padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            // Pass the UI state to the EntryBody
            EntryBody(
                insertSiswaUiState = uiState,
                onSiswaValueChange = { updatedValue ->
                    viewModel.updateSwaState(updatedValue) // Update ViewModel state
                },
                onSaveClick = {
                    uiState.insertSiswaUiEvent?.let { insertUiEvent ->
                        coroutineScope.launch {
                            viewModel.updateSwa(
                                id_siswa = viewModel.id_siswa,
                                siswa = insertUiEvent.toSwa()
                            )
                            navigateBack() // Navigate back after saving
                        }
                    }
                }
            )
        }
    }
}
