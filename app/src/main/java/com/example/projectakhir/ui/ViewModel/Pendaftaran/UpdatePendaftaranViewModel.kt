package com.example.projectakhir.ui.ViewModel.Pendaftaran

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhir.Repository.PendaftaranRepository
import com.example.projectakhir.model.Pendaftaran
import com.example.projectakhir.ui.View.Pendaftaran.DestinasiUpdatePendaftaran
import kotlinx.coroutines.launch

class UpdatePendaftaranViewModel(
    savedStateHandle: SavedStateHandle,
    private val pndftrn: PendaftaranRepository
) : ViewModel() {

    val id_pendaftaran: String = checkNotNull(savedStateHandle[DestinasiUpdatePendaftaran.ID_Pendaftaran])

    var uiState = mutableStateOf(InsertPendaftaranUiState())
        private set

    init {
        ambilPendaftaran()
    }

    private fun ambilPendaftaran() {
        viewModelScope.launch {
            try {
                // Konversi id_pendaftaran menjadi Int sebelum dipakai
                val idPendaftaranInt = id_pendaftaran.toInt()
                val pendaftaran = pndftrn.getPendaftaranbyid_pendaftaran(idPendaftaranInt)
                pendaftaran?.let {
                    uiState.value = it.toInsertPndftrnUIEvent()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun updatePndftrn(id_pendaftaran: Int, pendaftaran: Pendaftaran) {
        viewModelScope.launch {
            try {
                pndftrn.updatePendaftaran(id_pendaftaran, pendaftaran)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updatePndftrnState(insertPendaftranUiEvent: InsertPendaftranUiEvent) {
        uiState.value = uiState.value.copy(insertPendftaranUiEvent = insertPendaftranUiEvent)
    }
}

fun Pendaftaran.toInsertPndftrnUIEvent(): InsertPendaftaranUiState = InsertPendaftaranUiState(
    insertPendftaranUiEvent = this.toDetailPndftrnUiEvent()
)
