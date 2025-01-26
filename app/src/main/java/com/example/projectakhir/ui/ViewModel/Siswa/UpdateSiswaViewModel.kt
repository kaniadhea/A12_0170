package com.example.projectakhir.ui.ViewModel.Siswa


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhir.Repository.SiswaRepository
import com.example.projectakhir.model.Siswa
import com.example.projectakhir.ui.View.Siswa.DestinasiUpdateSiswa
import kotlinx.coroutines.launch

class UpdateSiswaViewModel (
    savedStateHandle: SavedStateHandle,
    private val swa: SiswaRepository
) : ViewModel(){

    val id_siswa: Int = checkNotNull(savedStateHandle[DestinasiUpdateSiswa.ID_Siswa])

    var uiState = mutableStateOf(InsertSiswaUiState())
        private set
    init {
        ambilSiswa()
    }


    private fun ambilSiswa() {
        viewModelScope.launch {
            try {
                val siswa = swa.getSiswabyid_siswa(id_siswa )
                siswa?.let {
                    uiState.value = it.toInsertSiswaUIEvent()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateSwa(id_siswa: String, siswa: Siswa) {
        viewModelScope.launch {
            try {
                val idSiswaInt = id_siswa.toInt() // Convert id_siswa from String to Int
                swa.updateSiswa(idSiswaInt, siswa) // Pass the Int to the update function
            } catch (e: NumberFormatException) {
                e.printStackTrace()
            }
        }
    }


    fun updateSwaState(insertSiswaUiEvent: InsertSiswaUiEvent) {
        uiState.value = uiState.value.copy(insertSiswaUiEvent = insertSiswaUiEvent)
    }
}

fun Siswa.toInsertSiswaUIEvent(): InsertSiswaUiState = InsertSiswaUiState(
    insertSiswaUiEvent = this.toDetailSiswaUiEvent())

