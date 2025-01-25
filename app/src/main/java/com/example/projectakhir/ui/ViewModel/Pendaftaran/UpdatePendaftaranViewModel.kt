package com.example.projectakhir.ui.ViewModel.Pendaftaran

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam11.ui.ViewModel.toDetailPndftrnUiEvent
import com.example.projectakhir.Repository.PendaftaranRepository
import com.example.projectakhir.model.Pendaftaran
import kotlinx.coroutines.launch

class UpdatePendaftaranViewModel (
    savedStateHandle: SavedStateHandle,
    private val pndftrn: PendaftaranRepository
) : ViewModel(){

    val id_pendaftaran: String = checkNotNull(savedStateHandle[DestinasiUpdate.NIM])

    var uiState = mutableStateOf(InsertPendaftaranUiState())
        private set
    init {
        ambilPendaftaran()
    }


    private fun ambilPendaftaran() {
        viewModelScope.launch {
            try {
                val pendaftaran = pndftrn.getPendaftaranbyid_pendaftaran(id_pendaftaran)
                pendaftaran?.let {
                    uiState.value = it.toInsertPndftrnUIEvent()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    fun updatePndftrn(id_pendaftaran: String, pendaftaran: Pendaftaran) {
        viewModelScope.launch {
            try {
                pndftrn.updatePendaftaran(id_pendaftaran, pendaftaran)


            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updatePndftrnState(insertPendaftaranUiState: InsertPendaftaranUiState) {
        uiState.value = uiState.value.copy(insertPendftaranUiEvent = InsertPendaftranUiEvent)
    }
}

fun Pendaftaran.toInsertPndftrnUIEvent(): InsertPendaftaranUiState = InsertPendaftaranUiState(
    insertPendftaranUiEvent = this.toDetailPndftrnUiEvent())

