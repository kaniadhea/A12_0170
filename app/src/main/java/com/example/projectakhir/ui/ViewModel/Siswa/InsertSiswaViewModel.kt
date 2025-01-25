package com.example.projectakhir.ui.ViewModel.Siswa

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectakhir.Repository.SiswaRepository
import com.example.projectakhir.model.Siswa
import kotlinx.coroutines.launch


class InsertSiswaViewModel(private val swa: SiswaRepository): ViewModel() {
    var uiState by mutableStateOf(InsertSiswaUiState())
        private set

    fun updateInsertSiswaState(insertSiswaUiEvent: InsertSiswaUiEvent){
        uiState = InsertSiswaUiState(insertSiswaUiEvent = insertSiswaUiEvent)
    }

    suspend fun insertMhs() {
        viewModelScope.launch {
            try {
                swa.insertSiswa(uiState.insertSiswaUiEvent.toSwa())
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}


data class InsertSiswaUiState(
    val insertSiswaUiEvent: InsertSiswaUiEvent = InsertSiswaUiEvent()
)

data class InsertSiswaUiEvent(

    val id_siswa: String = "",
    val nama_siswa: String = "",
    val email: String = "",
    val nomor_telepon: String = ""
)

fun InsertSiswaUiEvent.toSwa(): Siswa = Siswa(
    id_siswa =  id_siswa,
    nama_siswa = nama_siswa,
    email = email,
    nomor_telepon = nomor_telepon
)

fun Siswa.toUiStateSwa(): InsertSiswaUiState = InsertSiswaUiState(
    insertSiswaUiEvent = toInsertSiswaUiEvent()
)

fun Siswa.toInsertSiswaUiEvent(): InsertSiswaUiEvent = InsertSiswaUiEvent(
    id_siswa =  id_siswa,
    nama_siswa = nama_siswa,
    email = email,
    nomor_telepon = nomor_telepon
)