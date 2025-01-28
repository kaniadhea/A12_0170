package com.example.projectakhir.ui.View.Instruktur

import com.example.projectakhir.ui.ViewModel.PenyediaViewModel
import com.example.projectakhir.ui.ViewModel.Instruktur.UpdateInstrukturViewModel
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
import com.example.projectakhir.ui.ViewModel.Instruktur.toInstru
import kotlinx.coroutines.launch

object DestinasiUpdateInstruktur : DestinasiNavigasi {
    override val route = "update"
    const val ID_Instru = "id_instru"
    val routesWithArg = "$route/{$ID_Instru}"
    override val titleRes = "Update Mhs"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateInstrukturView(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    viewModel: UpdateInstrukturViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val uiState = viewModel.uiState.value

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiUpdateInstruktur.titleRes,
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
            EntryBodyIntruktur(
                insertInstrukturUiState = uiState,
                onInstruValueChange = { updateInstruValue ->
                    viewModel.updateInstruState(updateInstruValue) // Update ViewModel state
                },
                onSaveClick = {
                    uiState.insertInstrukturUiEvent?.let { insertInstruUiEvent ->
                        coroutineScope.launch {
                            viewModel.updateInstru(
                                id_instruktur = viewModel.id_instruktur,
                                instruktur = insertInstruUiEvent.toInstru()
                            )
                            navigateBack() // Navigate back after saving
                        }
                    }
                }
            )
        }
    }
}
