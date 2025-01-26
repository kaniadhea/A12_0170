package com.example.projectakhir.ui.ViewModel.Instruktur


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhir.Repository.InstrukturRepository
import com.example.projectakhir.model.Instruktur
import com.example.projectakhir.ui.View.Instruktur.DestinasiDetailInstruktur
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class DetailInstruUiState {
    data class Success(val instruktur: Instruktur) : DetailInstruUiState()
    object Error : DetailInstruUiState()
    object Loading : DetailInstruUiState()
}

class DetailInstrukturViewModel(
    savedStateHandle: SavedStateHandle,
    private val instru: InstrukturRepository
) : ViewModel() {

    private val _id_instruktur: Int = checkNotNull(savedStateHandle[DestinasiDetailInstruktur.ID_Instru])


    private val _detailInstrukturUiState = MutableStateFlow<DetailInstruUiState>(DetailInstruUiState.Loading)
    val detailIntrukturUiState: StateFlow<DetailInstruUiState> = _detailInstrukturUiState

    init {
        getDetailInstruktur()
    }

    fun getDetailInstruktur() {
        viewModelScope.launch {
            try {
                _detailInstrukturUiState.value = DetailInstruUiState.Loading
                val instruktur = instru.getInstrukturbyid_instruktur(_id_instruktur)

                if (instruktur != null) {
                    _detailInstrukturUiState.value = DetailInstruUiState.Success(instruktur)
                } else {
                    _detailInstrukturUiState.value = DetailInstruUiState.Error
                }
            } catch (e: Exception) {
                _detailInstrukturUiState.value = DetailInstruUiState.Error
            }
        }
    }
}


fun Instruktur.toDetailIntrukturUiEvent(): InsertInstrukturUiEvent {
    return InsertInstrukturUiEvent(
        id_instruktur = id_instruktur,
        nama_instruktur = nama_instruktur,
        email = email,
        nomor_telepon = nomor_telepon,
        deskripsi = deskripsi
    )
}