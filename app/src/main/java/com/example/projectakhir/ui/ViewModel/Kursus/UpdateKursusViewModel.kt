package com.example.projectakhir.ui.ViewModel.Kursus



import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhir.model.Kursus
import com.example.projectakhir.repository.KursusRepository
import com.example.projectakhir.ui.View.Kursus.DestinasiDetailKursus
import kotlinx.coroutines.launch

class UpdateKursusViewModel (
    savedStateHandle: SavedStateHandle,
    private val kurs: KursusRepository
) : ViewModel(){

    // Ubah id_instruktur menjadi Int
    val id_kursus: Int = checkNotNull(savedStateHandle[DestinasiDetailKursus.ID_Kursus]) // Pastikan ini merupakan ID yang dikirim dalam bentuk Int

    var uiState = mutableStateOf(InsertKursusUiState())
        private set

    init {
        ambilKursus()
    }

    private fun ambilKursus() {
        viewModelScope.launch {
            try {
                // Perhatikan bahwa kita mengubah id_instruktur menjadi Int
                val kursus = kurs.getKursusbyid_kursus(id_kursus)
                kursus?.let {
                    uiState.value = it.toInsertKursusUiState()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateKurs(id_kursus: Int, kursus: Kursus) { // Perubahan di sini:  menjadi Int
        viewModelScope.launch {
            try {
                // Pastikan updateInstruktur menerima id_instruktur dalam tipe Int
                kurs.updateKursus(id_kursus, kursus)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateKurState(insertKursusUiEvent: InsertKursusUiEvent) {
        uiState.value = uiState.value.copy(insertKursusUiEvent = insertKursusUiEvent)
    }
}

fun Kursus.toInsertKursusUiState(): InsertKursusUiState = InsertKursusUiState(
    insertKursusUiEvent = this.toDetailKursUiEvent())
