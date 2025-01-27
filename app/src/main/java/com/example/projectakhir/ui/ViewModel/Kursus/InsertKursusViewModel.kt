package com.example.projectakhir.ui.ViewModel.Kursus

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhir.Repository.InstrukturRepository
import com.example.projectakhir.model.Instruktur
import com.example.projectakhir.model.Kursus
import com.example.projectakhir.repository.KursusRepository
import kotlinx.coroutines.launch

class InsertKursusViewModel(private val kurs: KursusRepository) : ViewModel() {
    var kursUiState by mutableStateOf(InsertKursusUiState())
        private set

    fun updateInsertKursState(insertKursusUiEvent: InsertKursusUiEvent) {
        kursUiState = InsertKursusUiState(insertKursusUiEvent = insertKursusUiEvent)
    }

    fun insertKurs() { // Menghapus "suspend" karena sudah dipanggil di dalam "viewModelScope.launch"
        viewModelScope.launch {
            try {
                val kursus = kursUiState.insertKursusUiEvent.toKurs()
                kurs.insertKursus(kursus)
                println("Insert successful: $kursus") // Debugging log
            } catch (e: Exception) {
                e.printStackTrace()
                println("Error during insert: ${e.message}") // Error handling log
            }
        }
    }
}

// Data class untuk menyimpan UI state
data class InsertKursusUiState(
    val insertKursusUiEvent: InsertKursusUiEvent = InsertKursusUiEvent()
)

// Data class untuk event UI
data class InsertKursusUiEvent(

    val id_kursus: Int = 0,
    val nama_kursus: String = "",
    val deskripsi: String = "",
    val kategori: String = "",
    val harga: String = "",
    val id_instruktur: Int = 0
)

// Extension function untuk konversi InsertInstrukturUiEvent ke Instruktur
fun InsertKursusUiEvent.toKurs(): Kursus = Kursus(
    id_kursus = id_kursus,
    nama_kursus = nama_kursus,
    deskripsi = deskripsi,
    kategori = kategori,
    harga = harga,
    id_instruktur = id_instruktur
)

// Extension function untuk konversi Instruktur ke InsertInstrukturUiState
fun Kursus.toUiStateKurs(): InsertKursusUiState = InsertKursusUiState(
    insertKursusUiEvent = toInsertKursusUiEvent()
)

// Extension function untuk konversi Instruktur ke InsertInstrukturUiEvent
fun Kursus.toInsertKursusUiEvent(): InsertKursusUiEvent = InsertKursusUiEvent(
    id_kursus = id_kursus,
    nama_kursus = nama_kursus,
    deskripsi = deskripsi,
    kategori = kategori,
    harga = harga,
    id_instruktur = id_instruktur
)




