package com.example.projectakhir.ui.ViewModel.Instruktur

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhir.Repository.InstrukturRepository
import com.example.projectakhir.model.Instruktur
import kotlinx.coroutines.launch

class InsertInstrukturViewModel(private val instru: InstrukturRepository) : ViewModel() {
    var instruUiState by mutableStateOf(InsertInstrukturUiState())
        private set

    fun updateInsertInstruState(insertInstrukturUiEvent: InsertInstrukturUiEvent) {
        instruUiState = InsertInstrukturUiState(insertInstrukturUiEvent = insertInstrukturUiEvent)
    }

    fun insertInstru() { // Menghapus "suspend" karena sudah dipanggil di dalam "viewModelScope.launch"
        viewModelScope.launch {
            try {
                val instruktur = instruUiState.insertInstrukturUiEvent.toInstru()
                instru.insertInstruktur(instruktur)
                println("Insert successful: $instruktur") // Debugging log
            } catch (e: Exception) {
                e.printStackTrace()
                println("Error during insert: ${e.message}") // Error handling log
            }
        }
    }
}

// Data class untuk menyimpan UI state
data class InsertInstrukturUiState(
    val insertInstrukturUiEvent: InsertInstrukturUiEvent = InsertInstrukturUiEvent()
)

// Data class untuk event UI
data class InsertInstrukturUiEvent(
    val id_instruktur: Int = 0,
    val nama_instruktur: String = "",
    val email: String = "",
    val nomor_telepon: String = "",
    val deskripsi: String = ""
)

// Extension function untuk konversi InsertInstrukturUiEvent ke Instruktur
fun InsertInstrukturUiEvent.toInstru(): Instruktur = Instruktur(
    id_instruktur = id_instruktur,
    nama_instruktur = nama_instruktur,
    email = email,
    nomor_telepon = nomor_telepon,
    deskripsi = deskripsi
)

// Extension function untuk konversi Instruktur ke InsertInstrukturUiState
fun Instruktur.toUiStateInstru(): InsertInstrukturUiState = InsertInstrukturUiState(
    insertInstrukturUiEvent = toInsertInstrukturUiEvent()
)

// Extension function untuk konversi Instruktur ke InsertInstrukturUiEvent
fun Instruktur.toInsertInstrukturUiEvent(): InsertInstrukturUiEvent = InsertInstrukturUiEvent(
    id_instruktur = id_instruktur,
    nama_instruktur = nama_instruktur,
    email = email,
    nomor_telepon = nomor_telepon,
    deskripsi = deskripsi
)
