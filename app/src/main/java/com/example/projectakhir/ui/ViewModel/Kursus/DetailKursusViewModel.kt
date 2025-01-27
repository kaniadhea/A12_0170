package com.example.projectakhir.ui.ViewModel.Kursus

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhir.model.Kursus
import com.example.projectakhir.repository.KursusRepository
import com.example.projectakhir.ui.View.Kursus.DestinasiDetailKursus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class DetailKursusUiState {
    data class Success(val kursus: Kursus) : DetailKursusUiState()
    object Error : DetailKursusUiState()
    object Loading : DetailKursusUiState()
}

class DetailKursusViewModel(
    savedStateHandle: SavedStateHandle,
    private val kur: KursusRepository
) : ViewModel() {

    private val _id_kursus: Int = checkNotNull(savedStateHandle[DestinasiDetailKursus.ID_Kursus])

    private val _detailUiState = MutableStateFlow<DetailKursusUiState>(DetailKursusUiState.Loading)
    val detailKursusUiState: StateFlow<DetailKursusUiState> = _detailUiState

    init {
        getDetailKursus()
    }

    fun getDetailKursus() {
        viewModelScope.launch {
            try {
                _detailUiState.value = DetailKursusUiState.Loading
                val kursus = kur.getKursusbyid_kursus(_id_kursus)

                if (kursus != null) {
                    _detailUiState.value = DetailKursusUiState.Success(kursus)
                } else {
                    _detailUiState.value = DetailKursusUiState.Error
                }
            } catch (e: Exception) {
                _detailUiState.value = DetailKursusUiState.Error
            }
        }
    }
}

fun Kursus.toDetailKursUiEvent(): InsertKursusUiEvent {
    return InsertKursusUiEvent(
        id_kursus = id_kursus,
        nama_kursus = nama_kursus,
        deskripsi = deskripsi,
        kategori = kategori,
        harga = harga,
        id_instruktur = id_instruktur
    )
}
