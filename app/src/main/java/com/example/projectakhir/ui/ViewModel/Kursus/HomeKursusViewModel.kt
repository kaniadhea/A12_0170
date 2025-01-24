package com.example.projectakhir.ui.ViewModel.Kursus

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.projectakhir.Repository.KursusRepository
import com.example.projectakhir.model.Kursus
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
            kurUiState = try {
                HomeKursusUiState.Success(kur.getKursus())
            } catch (e: IOException) {
                HomeKursusUiState.Error
            } catch (e: HttpException) {
                HomeKursusUiState.Error
            }
        }
    }


    fun deleteKur(id_kursus: String) {
        viewModelScope.launch {
            try {
                kur.deleteKursus(id_kursus)
            } catch (e: IOException) {
                HomeKursusUiState.Error
            } catch (e: HttpException) {
                HomeKursusUiState.Error
            }
        }
    }
}


