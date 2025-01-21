package com.example.pam11.ui.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhir.Repository.InstrukturRepository

import com.example.projectakhir.model.Instruktur
import kotlinx.coroutines.launch


class InsertViewModel(private val instru: InstrukturRepository): ViewModel() {
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertInstruState(insertUiEvent: InsertUiEvent){
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertInstruktur() {
        viewModelScope.launch {
            try {
                instru.insertInstruktur(uiState.insertUiEvent.toInstru())
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}


data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)

data class InsertUiEvent (
    val id_instruktur: String="",
    val nama_instruktur: String="",
    val email: String="",
    val nomor_telepon: String="",
    val deskripsi:String=""
)

fun InsertUiEvent.toInstru(): Instruktur = Instruktur(
    id_instruktur = id_instruktur,
    nama_instruktur = nama_instruktur,
    email = email,
    nomor_telepon = nomor_telepon,
    deskripsi = deskripsi
)

fun Instruktur.toUiStateInstru(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Instruktur.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    id_instruktur = id_instruktur,
    nama_instruktur = nama_instruktur,
    email = email,
    nomor_telepon = nomor_telepon,
    deskripsi = deskripsi
)