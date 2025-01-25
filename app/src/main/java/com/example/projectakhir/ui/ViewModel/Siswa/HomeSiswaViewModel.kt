package com.example.projectakhir.ui.ViewModel.Siswa

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.projectakhir.Repository.SiswaRepository
import com.example.projectakhir.model.Siswa
import kotlinx.coroutines.launch
import okio.IOException

sealed class HomeSiswaUiState {
    data class Success(val siswa: List<Siswa>) : HomeSiswaUiState()
    object Error : HomeSiswaUiState()
    object Loading : HomeSiswaUiState()
}

class HomeSiswaViewModel(private val swa: SiswaRepository) : ViewModel() {
    var swaUiState: HomeSiswaUiState by mutableStateOf(HomeSiswaUiState.Loading)
        private set


    init {
        getSwa()
    }


    fun getSwa() {
        viewModelScope.launch {
            swaUiState = HomeSiswaUiState.Loading
            swaUiState = try {
                HomeSiswaUiState.Success(swa.getSiswa())
            } catch (e: IOException) {
                HomeSiswaUiState.Error
            } catch (e: HttpException) {
                HomeSiswaUiState.Error
            }
        }
    }


    fun deleteMhs(nim: String) {
        viewModelScope.launch {
            try {
                swa.deleteSiswa(nim)
            } catch (e: IOException) {
                HomeSiswaUiState.Error
            } catch (e: HttpException) {
                HomeSiswaUiState.Error
            }
        }
    }
}


