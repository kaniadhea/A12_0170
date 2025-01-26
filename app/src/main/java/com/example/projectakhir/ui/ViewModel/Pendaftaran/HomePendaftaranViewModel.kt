package com.example.projectakhir.ui.ViewModel.Pendaftaran

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.projectakhir.Repository.PendaftaranRepository
import com.example.projectakhir.model.Pendaftaran
import com.example.projectakhir.ui.ViewModel.Siswa.HomeSiswaUiState
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
                HomePendaftaranUiState.Success(pndftn.getAllPendaftaran().data)
            } catch (e: IOException) {
                e.printStackTrace() // Log error ke console
                HomePendaftaranUiState.Error
            } catch (e: HttpException) {
                e.printStackTrace() // Log error ke console
                HomePendaftaranUiState.Error
            }
        }
    }



    fun deletePndftrn(id_pendaftaran: Int) {
        viewModelScope.launch {
            try {
                // Pastikan parameter id_pendaftaran yang dikirim sesuai dengan tipe data yang diharapkan di repository
                pndftn.deletePendaftaran(id_pendaftaran)
            } catch (e: IOException) {
                pdftnUiState = HomePendaftaranUiState.Error
            } catch (e: HttpException) {
                pdftnUiState = HomePendaftaranUiState.Error
            }
        }
    }

}


