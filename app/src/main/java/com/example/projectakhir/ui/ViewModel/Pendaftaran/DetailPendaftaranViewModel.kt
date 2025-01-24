package com.example.projectakhir.ui.ViewModel.Pendaftaran

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.projectakhir.Repository.PendaftaranRepository
import com.example.projectakhir.model.Pendaftaran
import kotlinx.coroutines.launch
import okio.IOException

sealed class HomePendaftaranUiState {
    data class Success(val pendaftaran: List<Pendaftaran>) : HomePendaftaranUiState()
    object Error : HomePendaftaranUiState()
    object Loading : HomePendaftaranUiState()
}

class HomePendaftaranViewModel(private val pen: PendaftaranRepository) : ViewModel() {
    var penUiState: HomePendaftaranUiState by mutableStateOf(HomePendaftaranUiState.Loading)
        private set


    init {
        getPen()
    }


    fun getPen() {
        viewModelScope.launch {
            penUiState = HomePendaftaranUiState.Loading
            penUiState = try {
                HomePendaftaranUiState.Success(pen.getPendaftaran())
            } catch (e: IOException) {
                HomePendaftaranUiState.Error
            } catch (e: HttpException) {
                HomePendaftaranUiState.Error
            }
        }
    }


    fun deletePen(id_pendaftaran: String) {
        viewModelScope.launch {
            try {
                pen.deletePendaftaran(id_pendaftaran)
            } catch (e: IOException) {
                HomePendaftaranUiState.Error
            } catch (e: HttpException) {
                HomePendaftaranUiState.Error
            }
        }
    }
}


