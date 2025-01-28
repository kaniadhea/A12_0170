package com.example.projectakhir.ui.View.Pendaftaran

import com.example.projectakhir.ui.ViewModel.Pendaftaran.UpdatePendaftaranViewModel
import com.example.projectakhir.ui.ViewModel.Pendaftaran.toPndftran
import com.example.projectakhir.ui.ViewModel.PenyediaViewModel
import com.example.projectakhir.ui.navigasi.CostumeTopAppBar
import com.example.projectakhir.ui.navigasi.DestinasiNavigasi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

object DestinasiUpdatePendaftaran : DestinasiNavigasi {
    override val route = "updatependaftaran"
    const val ID_Pendaftaran = "id_pendaftaran" // Menggunakan ID_Pendaftaran sebagai Int
    val routeWithArg = "$route/{$ID_Pendaftaran}"
    override val titleRes = "Update Pendaftaran"
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePendfatranView(
    onBack: () -> Unit,
    id_pendaftaran: Int, // id_pendaftaran sekarang bertipe Int
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdatePendaftaranViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val uiState = viewModel.uiState.value

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiUpdatePendaftaran.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            // Pass the UI state to the EntryBody
            EntryBodyPndftrn(
                insertPendaftaranUiState = uiState,
                onSiswaValueChange = { updatedValue ->
                    viewModel.updatePndftrnState(updatedValue) // Update ViewModel state
                },
                onSaveClick = {
                    uiState.insertPendftaranUiEvent?.let { insertPndftrnEvent ->
                        coroutineScope.launch {
                            // Pass the ID as Int for the update
                            val idPendaftaran = uiState.insertPendftaranUiEvent?.id_pendaftaran ?: 0
                            viewModel.updatePndftrn(
                                id_pendaftaran = idPendaftaran,  // ID should be passed as Int
                                pendaftaran = insertPndftrnEvent.toPndftran()
                            )
                            navigateBack() // Navigate back after saving
                        }
                    }
                }
            )
        }
    }
}
