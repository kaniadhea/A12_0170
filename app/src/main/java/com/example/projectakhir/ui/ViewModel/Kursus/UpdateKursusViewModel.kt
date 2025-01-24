package com.example.projectakhir.ui.ViewModel.Kursus

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhir.Repository.KursusRepository
import com.example.projectakhir.model.Kursus
import com.example.projectakhir.ui.View.Kursus.DestinasiUpdateKursus
import kotlinx.coroutines.launch

class UpdateKursusViewModel (
    savedStateHandle: SavedStateHandle,
    private val kur: KursusRepository
) : ViewModel(){

    val id_kursus: String = checkNotNull(savedStateHandle[DestinasiUpdateKursus.ID_Kursus])

    var uiState = mutableStateOf(InsertKursusUiState())
        private set
    init {
        ambilKursus()
    }


    private fun ambilKursus() {
        viewModelScope.launch {
            try {
                val kursus = kur.getKursusbyid_kursus(id_kursus)
                kursus?.let {
                    uiState.value = it.toInsertKursusUIEvent()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    fun updateKur(id_kursus: String, kursus: Kursus) {
        viewModelScope.launch {
            try {
                kur.updateKursus(id_kursus, kursus)


            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateKurState(insertKursusUiEvent: InsertKursusUiEvent) {
        uiState.value = uiState.value.copy(insertKursusUiEvent = insertKursusUiEvent)
    }
}

fun Kursus.toInsertKursusUIEvent(): InsertKursusUiState = InsertKursusUiState(
    insertKursusUiEvent = this.toDetailKursusUiEvent())

