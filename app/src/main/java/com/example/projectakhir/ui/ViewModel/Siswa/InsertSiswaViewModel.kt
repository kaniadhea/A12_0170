package com.example.projectakhir.ui.ViewModel.Siswa

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhir.Repository.SiswaRepository
import com.example.projectakhir.model.Siswa
import kotlinx.coroutines.launch

class InsertSiswaViewModel(private val swa: SiswaRepository): ViewModel() {
    var SiswauiState by mutableStateOf(InsertSiswaUiState())
        private set

    fun updateInsertSwaState(insertSiswaUiEvent: InsertSiswaUiEvent){
        SiswauiState = InsertSiswaUiState(insertSiswaUiEvent = insertSiswaUiEvent)
    }

    suspend fun insertSwa() {
        viewModelScope.launch {
            try {
                val siswa = SiswauiState.insertSiswaUiEvent.toSwa()
                swa.insertSiswa(siswa)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class InsertSiswaUiState(
    val insertSiswaUiEvent: InsertSiswaUiEvent = InsertSiswaUiEvent()
)

data class InsertSiswaUiEvent(
    val id_siswa: Int = 0,
    val nama_siswa: String = "",
    val email: String = "",
    val nomor_telepon: String = ""
)

fun InsertSiswaUiEvent.toSwa(): Siswa = Siswa(
    id_siswa = id_siswa,  // Ubah menjadi Int
    nama_siswa = nama_siswa,
    email = email,
    nomor_telepon = nomor_telepon
)

fun Siswa.toUiStateSwa(): InsertSiswaUiState = InsertSiswaUiState(
    insertSiswaUiEvent = toInsertSiswaUiEvent()
)

fun Siswa.toInsertSiswaUiEvent(): InsertSiswaUiEvent = InsertSiswaUiEvent(
    id_siswa = id_siswa,  // Ubah menjadi Int
    nama_siswa = nama_siswa,
    email = email,
    nomor_telepon = nomor_telepon
)
