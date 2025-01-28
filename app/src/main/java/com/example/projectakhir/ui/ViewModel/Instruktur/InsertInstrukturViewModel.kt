package com.example.projectakhir.ui.ViewModel.Instruktur

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectakhir.Repository.InstrukturRepository
import com.example.projectakhir.model.Instruktur
import com.example.projectakhir.model.Kursus
import kotlinx.coroutines.launch


class InsertInstrukturViewModel(private val instru: InstrukturRepository): ViewModel() {
    var instruuiState by mutableStateOf(InsertInstrukturUiState())
        private set

    fun updateInsertInstruState(insertInstrukturUiEvent: InsertInstrukturUiEvent) {
        instruuiState = InsertInstrukturUiState(insertInstrukturUiEvent = insertInstrukturUiEvent)
    }

    suspend fun insertInstru() {
        viewModelScope.launch {
            try {
                Log.d("InsertInstruktur", "Inserting data: ${instruuiState.insertInstrukturUiEvent}")
                instru.insertInstruktur(instruuiState.insertInstrukturUiEvent.toInstru())
            } catch (e: Exception) {
                Log.e("InsertInstruktur", "Error inserting data", e)
            }
        }
    }
}



data class InsertInstrukturUiState(
    val insertInstrukturUiEvent: InsertInstrukturUiEvent = InsertInstrukturUiEvent()
)

data class InsertInstrukturUiEvent(
    val id_instruktur: Int = 0,
    val nama_instruktur: String = "",
    val email: String = "",
    val nomor_telepon: String = "",
    val deskripsi: String = ""
)

fun InsertInstrukturUiEvent.isValid(): Boolean {
    return nama_instruktur.isNotBlank() && email.isNotBlank() && email.contains("@") && nomor_telepon.isNotBlank()
}

fun InsertInstrukturUiEvent.toInstru(): Instruktur = Instruktur(
    id_instruktur = id_instruktur,
    nama_instruktur = nama_instruktur,
    email = email,
    nomor_telepon = nomor_telepon,
    deskripsi = deskripsi
)
