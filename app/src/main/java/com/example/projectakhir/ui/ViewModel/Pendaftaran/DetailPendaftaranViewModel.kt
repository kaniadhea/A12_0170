package com.example.pam11.ui.ViewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhir.Repository.PendaftaranRepository
import com.example.projectakhir.model.Pendaftaran
import com.example.projectakhir.ui.View.Pendaftaran.DestinasiDetailPendaftran
import com.example.projectakhir.ui.ViewModel.Pendaftaran.InsertPendaftranUiEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class DetailPendaftaranUiState {
    data class Success(val pendaftaran: Pendaftaran) : DetailPendaftaranUiState()
    object Error : DetailPendaftaranUiState()
    object Loading : DetailPendaftaranUiState()
}

class DetailPendaftaranViewModel(
    savedStateHandle: SavedStateHandle,
    private val pndftrn: PendaftaranRepository
) : ViewModel() {

    private val _id_pendaftaran: String = checkNotNull(savedStateHandle[DestinasiDetailPendaftran.ID_Pendaftaran])


    private val _detailPendaftaranUiState = MutableStateFlow<DetailPendaftaranUiState>(DetailPendaftaranUiState.Loading)
    val detailPndftrnUiState: StateFlow<DetailPendaftaranUiState> = _detailPendaftaranUiState

    init {
        getDetailPndftrn()
    }

    fun getDetailPndftrn() {
        viewModelScope.launch {
            try {
                _detailPendaftaranUiState.value = DetailPendaftaranUiState.Loading
                val pendaftaran = pndftrn.getPendaftaranbyid_pendaftaran(_id_pendaftaran)

                if (pendaftaran != null) {
                    _detailPendaftaranUiState.value = DetailPendaftaranUiState.Success(pendaftaran)
                } else {
                    _detailPendaftaranUiState.value = DetailPendaftaranUiState.Error
                }
            } catch (e: Exception) {
                _detailPendaftaranUiState.value = DetailPendaftaranUiState.Error
            }
        }
    }
}


fun Pendaftaran.toDetailPndftrnUiEvent(): InsertPendaftranUiEvent {
    return InsertPendaftranUiEvent(
        id_pendaftaran = id_pendaftaran,
        id_kursus = id_kursus,
        id_siswa = id_siswa,
        tanggal_pendaftaran = tanggal_pendaftaran
    )
}