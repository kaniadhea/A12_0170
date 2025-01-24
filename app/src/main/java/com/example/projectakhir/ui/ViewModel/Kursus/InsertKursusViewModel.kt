package com.example.projectakhir.ui.ViewModel.Kursus

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhir.model.Kursus
import com.example.projectakhir.repository.KursusRepository
import kotlinx.coroutines.launch


class InsertKursusViewModel(private val kur: KursusRepository): ViewModel() {
    var KursusuiState by mutableStateOf(InsertKursusUiState())
        private set

    fun updateInsertKursusState(insertKursusUiEvent: InsertKursusUiEvent){
        KursusuiState = InsertKursusUiState(insertKursusUiEvent = insertKursusUiEvent)
    }

    suspend fun insertKursus() {
        viewModelScope.launch {
            try {
                kur.insertKursus(KursusuiState.insertKursusUiEvent.toKur())
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}


data class InsertKursusUiState(
    val insertKursusUiEvent: InsertKursusUiEvent = InsertKursusUiEvent()
)

data class InsertKursusUiEvent(

    val id_kursus: String="",
    val nama_kursus: String="",
    val deskripsi: String="",
    val kategori: String="",
    val harga: String="",
    val id_instruktur: String=""
)

fun InsertKursusUiEvent.toKur(): Kursus = Kursus(

    id_kursus = id_kursus,
    nama_kursus = nama_kursus,
    deskripsi = deskripsi,
    kategori = kategori,
    harga = harga,
    id_instruktur = id_instruktur


)

fun Kursus.toUiStateKur(): InsertKursusUiState = InsertKursusUiState(
    insertKursusUiEvent = toInsertUiEvent()
)

fun Kursus.toInsertUiEvent(): InsertKursusUiEvent = InsertKursusUiEvent(

    id_kursus = id_kursus,
    nama_kursus = nama_kursus,
    deskripsi = deskripsi,
    kategori = kategori,
    harga = harga,
    id_instruktur = id_instruktur
)