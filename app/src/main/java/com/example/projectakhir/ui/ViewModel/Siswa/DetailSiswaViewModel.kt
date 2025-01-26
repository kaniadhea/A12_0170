package com.example.projectakhir.ui.ViewModel.Siswa

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhir.Repository.SiswaRepository
import com.example.projectakhir.model.Siswa
import com.example.projectakhir.ui.View.Siswa.DestinasiDetailSiswa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class DetailSiswaUiState {
    data class Success(val siswa: Siswa) : DetailSiswaUiState()
    object Error : DetailSiswaUiState()
    object Loading : DetailSiswaUiState()
}

class DetailSiswaViewModel(
    savedStateHandle: SavedStateHandle,
    private val swa: SiswaRepository
) : ViewModel() {

    private val _id_siswa : Int = checkNotNull(savedStateHandle[DestinasiDetailSiswa.ID_Siswa])


    private val _detailSiswaUiState = MutableStateFlow<DetailSiswaUiState>(DetailSiswaUiState.Loading)
    val detailUiState: StateFlow<DetailSiswaUiState> = _detailSiswaUiState

    init {
        getDetailSiswa()
    }

    fun getDetailSiswa() {
        viewModelScope.launch {
            try {
                _detailSiswaUiState.value = DetailSiswaUiState.Loading
                val siswa = swa.getSiswabyid_siswa(_id_siswa)

                if (siswa != null) {
                    _detailSiswaUiState.value = DetailSiswaUiState.Success(siswa)
                } else {
                    _detailSiswaUiState.value = DetailSiswaUiState.Error
                }
            } catch (e: Exception) {
                _detailSiswaUiState.value = DetailSiswaUiState.Error
            }
        }
    }
}


fun Siswa.toDetailSiswaUiEvent(): InsertSiswaUiEvent {
    return InsertSiswaUiEvent(
        id_siswa =  id_siswa,
        nama_siswa = nama_siswa,
        email = email,
        nomor_telepon = nomor_telepon
    )
}