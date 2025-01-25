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

class HomePendaftaranViewModel(private val pndftn: PendaftaranRepository) : ViewModel() {
    var pdftnUiState: HomePendaftaranUiState by mutableStateOf(HomePendaftaranUiState.Loading)
        private set


    init {
        getpndftrn()
    }


    fun getpndftrn() {
        viewModelScope.launch {
            pdftnUiState = HomePendaftaranUiState.Loading
            pdftnUiState = try {
                HomePendaftaranUiState.Success(pndftn.getPendaftaran())
            } catch (e: IOException) {
                HomePendaftaranUiState.Error
            } catch (e: HttpException) {
                HomePendaftaranUiState.Error
            }
        }
    }


    fun deletePndftrn(id_pendaftaran: String) {
        viewModelScope.launch {
            try {
                pndftn.deletePendaftaran(id_pendaftaran)
            } catch (e: IOException) {
                HomePendaftaranUiState.Error
            } catch (e: HttpException) {
                HomePendaftaranUiState.Error
            }
        }
    }
}


