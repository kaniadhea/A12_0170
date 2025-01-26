package com.example.projectakhir.ui.ViewModel.Instruktur

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam11.ui.ViewModel.InsertInstrukturUiEvent
import com.example.pam11.ui.ViewModel.InsertInstrukturUiState
import com.example.pam11.ui.ViewModel.toInsertInstrukturUiEvent
import com.example.projectakhir.Repository.InstrukturRepository
import com.example.projectakhir.model.Instruktur
import com.example.projectakhir.ui.View.Kursus.DestinasiDetailKursus
import kotlinx.coroutines.launch

class UpdateInstrukturViewModel (
    savedStateHandle: SavedStateHandle,
    private val instru: InstrukturRepository
) : ViewModel(){

    // Ubah id_instruktur menjadi Int
    val id_instruktur: Int = checkNotNull(savedStateHandle[DestinasiDetailKursus.ID_Kursus]) // Pastikan ini merupakan ID yang dikirim dalam bentuk Int

    var uiState = mutableStateOf(InsertInstrukturUiState())
        private set

    init {
        ambilInstruktur()
    }

    private fun ambilInstruktur() {
        viewModelScope.launch {
            try {
                // Perhatikan bahwa kita mengubah id_instruktur menjadi Int
                val instruktur = instru.getInstrukturbyid_instruktur(id_instruktur)
                instruktur?.let {
                    uiState.value = it.toInsertInstrukturUIEvent()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateInstru(id_instruktur: Int, instruktur: Instruktur) { // Perubahan di sini: id_instruktur menjadi Int
        viewModelScope.launch {
            try {
                // Pastikan updateInstruktur menerima id_instruktur dalam tipe Int
                instru.updateInstruktur(id_instruktur, instruktur)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateInstruState(insertInstrukturUiEvent: InsertInstrukturUiEvent) {
        uiState.value = uiState.value.copy(insertInstrukturUiEvent = insertInstrukturUiEvent)
    }
}

fun Instruktur.toInsertInstrukturUIEvent(): InsertInstrukturUiState = InsertInstrukturUiState(
    insertInstrukturUiEvent = this.toDetailIntrukturUiEvent())
