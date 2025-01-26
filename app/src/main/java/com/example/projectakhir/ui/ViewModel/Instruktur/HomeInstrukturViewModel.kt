package com.example.projectakhir.ui.ViewModel.Instruktur

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.projectakhir.Repository.InstrukturRepository
import com.example.projectakhir.model.Instruktur
import kotlinx.coroutines.launch
import okio.IOException

sealed class HomeInstrukturUiState {
    data class Success(val instruktur: List<Instruktur>) : HomeInstrukturUiState()
    object Error : HomeInstrukturUiState()
    object Loading : HomeInstrukturUiState()
}

class HomeInstrukturViewModel(private val instru: InstrukturRepository) : ViewModel() {
    var instruUiState: HomeInstrukturUiState by mutableStateOf(HomeInstrukturUiState.Loading)
        private set

    init {
        getIntru()
    }

    fun getIntru() {
        viewModelScope.launch {
            instruUiState = HomeInstrukturUiState.Loading
            try {
                // Mendapatkan data AllInstrukturRespons dan mengubahnya menjadi List<Instruktur>
                val response = instru.getAllInstruktur()
                // Misalkan `response.data` adalah list instruktur yang kita butuhkan
                instruUiState = HomeInstrukturUiState.Success(response.data) // Pastikan `data` adalah List<Instruktur>
            } catch (e: IOException) {
                instruUiState = HomeInstrukturUiState.Error
            } catch (e: HttpException) {
                instruUiState = HomeInstrukturUiState.Error
            }
        }
    }

    fun deleteInstru(id_instruktur: Int) {
        viewModelScope.launch {
            try {
                instru.deleteInstruktur(id_instruktur)
            } catch (e: IOException) {
                instruUiState = HomeInstrukturUiState.Error
            } catch (e: HttpException) {
                instruUiState = HomeInstrukturUiState.Error
            }
        }
    }
}
