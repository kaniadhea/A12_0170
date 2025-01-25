package com.example.pam11.ui.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhir.Repository.InstrukturRepository

import com.example.projectakhir.model.Instruktur
import kotlinx.coroutines.launch


class InsertInstrukturViewModel(private val instru: InstrukturRepository): ViewModel() {
    var InstruuiState by mutableStateOf(InsertInstrukturUiState())
        private set

    fun updateInsertInstrukturState(insertUiEvent: InsertInstrukturUiEvent){
        InstruuiState = InsertInstrukturUiState(insertInstrukturUiEvent = insertUiEvent)
    }

    suspend fun insertInst() {
        viewModelScope.launch {
            try {
                instru.insertInstruktur(InstruuiState.insertInstrukturUiEvent.toInstru())
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}


data class InsertInstrukturUiState(
    val insertInstrukturUiEvent: InsertInstrukturUiEvent = InsertInstrukturUiEvent()
)

data class InsertInstrukturUiEvent (
    val id_instruktur: String="",
    val nama_instruktur: String="",
    val email: String="",
    val nomor_telepon: String="",
    val deskripsi:String=""
)

fun InsertInstrukturUiEvent.toInstru(): Instruktur = Instruktur(
    id_instruktur = id_instruktur,
    nama_instruktur = nama_instruktur,
    email = email,
    nomor_telepon = nomor_telepon,
    deskripsi = deskripsi
)

fun Instruktur.toUiStateInstru(): InsertInstrukturUiState = InsertInstrukturUiState(
    insertInstrukturUiEvent = toInsertInstrukturUiEvent()
)

fun Instruktur.toInsertInstrukturUiEvent(): InsertInstrukturUiEvent = InsertInstrukturUiEvent(
    id_instruktur = id_instruktur,
    nama_instruktur = nama_instruktur,
    email = email,
    nomor_telepon = nomor_telepon,
    deskripsi = deskripsi
)