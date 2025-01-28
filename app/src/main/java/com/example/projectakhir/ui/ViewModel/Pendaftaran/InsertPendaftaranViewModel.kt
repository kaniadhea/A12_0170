package com.example.projectakhir.ui.ViewModel.Pendaftaran

import com.example.projectakhir.model.Pendaftaran
import kotlin.math.tan
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhir.Repository.PendaftaranRepository
import kotlinx.coroutines.launch



class InsertPendaftaranViewModel(private val pndftrn: PendaftaranRepository) : ViewModel() {
    var uiState by mutableStateOf(InsertPendaftaranUiState())
        private set

    fun updateInsertPndftrnState(insertPendftaranUiEvent: InsertPendaftranUiEvent) {
        uiState = InsertPendaftaranUiState(insertPendftaranUiEvent = insertPendftaranUiEvent)
    }

    suspend fun insertPndftrn() {
        viewModelScope.launch {
            try {
                pndftrn.insertPendaftaran(uiState.insertPendftaranUiEvent.toPndftran())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class InsertPendaftaranUiState(
    val insertPendftaranUiEvent: InsertPendaftranUiEvent = InsertPendaftranUiEvent()
)

data class InsertPendaftranUiEvent(
    val id_pendaftaran: Int = 0, // Mengubah ke tipe data Int
    val id_kursus: Int = 0, // Mengubah ke tipe data Int
    val id_siswa: Int = 0, // Mengubah ke tipe data Int
    val tanggal_pendaftaran: String = "" // Tetap String
)

fun InsertPendaftranUiEvent.toPndftran(): Pendaftaran = Pendaftaran(
    id_pendaftaran = id_pendaftaran,
    id_kursus = id_kursus,
    id_siswa = id_siswa,
    tanggal_pendaftaran = tanggal_pendaftaran
)

fun Pendaftaran.toUiStatePndftrn(): InsertPendaftaranUiState = InsertPendaftaranUiState(
    insertPendftaranUiEvent = toInsertPndftrnUiEvent()
)

fun Pendaftaran.toInsertPndftrnUiEvent(): InsertPendaftranUiEvent = InsertPendaftranUiEvent(
    id_pendaftaran = id_pendaftaran,
    id_kursus = id_kursus,
    id_siswa = id_siswa,
    tanggal_pendaftaran = tanggal_pendaftaran
)
