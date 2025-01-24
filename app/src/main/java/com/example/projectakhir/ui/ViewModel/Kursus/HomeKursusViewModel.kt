package com.example.projectakhir.ui.ViewModel.Kursus

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.projectakhir.model.Kursus
import com.example.projectakhir.repository.KursusRepository
import kotlinx.coroutines.launch
import okio.IOException

sealed class HomeKursusUiState {
    data class Success(val kursus: List<Kursus>) : HomeKursusUiState()
    object Error : HomeKursusUiState()
    object Loading : HomeKursusUiState()
}

class HomeKursusViewModel(private val kur: KursusRepository) : ViewModel() {
    var kurUiState: HomeKursusUiState by mutableStateOf(HomeKursusUiState.Loading)
        private set


    init {
        getKur()
    }


    fun getKur() {
        viewModelScope.launch {
            kurUiState = HomeKursusUiState.Loading
            try {
                val data = kur.getKursus()
                kurUiState = HomeKursusUiState.Success(data)
                println("Data kursus berhasil diambil: $data")
            } catch (e: IOException) {
                println("Error IOException: ${e.message}")
                kurUiState = HomeKursusUiState.Error
            } catch (e: HttpException) {
                println("Error HttpException: ${e.message}")
                kurUiState = HomeKursusUiState.Error
            }
        }
    }


    fun deleteKur(id_kursus: String) {
        viewModelScope.launch {
            try {
                kur.deleteKursus(id_kursus)
                getKur()
            } catch (e: IOException) {
                kurUiState = HomeKursusUiState.Error
            } catch (e: HttpException) {
                kurUiState = HomeKursusUiState.Error
            }
        }
    }
}


