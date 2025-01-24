package com.example.projectakhir.ui.ViewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pam11.ui.ViewModel.InsertInstrukturViewModel
import com.example.projectakhir.KursusApplications
import com.example.projectakhir.ui.ViewModel.Instruktur.DetailInstrukturViewModel
import com.example.projectakhir.ui.ViewModel.Instruktur.HomeInstrukturViewModel
import com.example.projectakhir.ui.ViewModel.Instruktur.UpdateInstrukturViewModel
import com.example.projectakhir.ui.ViewModel.Kursus.DetailKursusViewModel
import com.example.projectakhir.ui.ViewModel.Kursus.HomeKursusViewModel
import com.example.projectakhir.ui.ViewModel.Kursus.InsertKursusViewModel
import com.example.projectakhir.ui.ViewModel.Kursus.UpdateKursusViewModel

object PenyediaViewModel {

    val FactoryKursus = viewModelFactory {
        initializer { HomeKursusViewModel(aplikasiKontak().container.kursusRepository) }
        initializer { InsertKursusViewModel(aplikasiKontak().container.kursusRepository) }
        initializer { DetailKursusViewModel(createSavedStateHandle(), aplikasiKontak().container.kursusRepository) }
        initializer { UpdateKursusViewModel(createSavedStateHandle(), aplikasiKontak().container.kursusRepository) }
    }

    val FactoryInstruktur = viewModelFactory {
        initializer { HomeInstrukturViewModel(aplikasiKontak().container.instrukturRepository) }
        initializer { InsertInstrukturViewModel(aplikasiKontak().container.instrukturRepository) }
        initializer { DetailInstrukturViewModel(createSavedStateHandle(), aplikasiKontak().container.instrukturRepository) }
        initializer { UpdateInstrukturViewModel(createSavedStateHandle(), aplikasiKontak().container.instrukturRepository) }
    }
}

fun CreationExtras.aplikasiKontak(): KursusApplications =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KursusApplications)
