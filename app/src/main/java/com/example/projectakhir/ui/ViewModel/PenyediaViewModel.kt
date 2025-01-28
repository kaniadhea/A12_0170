package com.example.projectakhir.ui.ViewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.projectakhir.KursusApplications
import com.example.projectakhir.ui.ViewModel.Instruktur.DetailInstrukturViewModel
import com.example.projectakhir.ui.ViewModel.Instruktur.HomeInstrukturViewModel
import com.example.projectakhir.ui.ViewModel.Instruktur.InsertInstrukturViewModel
import com.example.projectakhir.ui.ViewModel.Instruktur.UpdateInstrukturViewModel
import com.example.projectakhir.ui.ViewModel.Kursus.DetailKursusViewModel
import com.example.projectakhir.ui.ViewModel.Kursus.HomeKursusViewModel
import com.example.projectakhir.ui.ViewModel.Kursus.InsertKursusViewModel
import com.example.projectakhir.ui.ViewModel.Kursus.UpdateKursusViewModel
import com.example.projectakhir.ui.ViewModel.Pendaftaran.DetailPendaftaranViewModel
import com.example.projectakhir.ui.ViewModel.Pendaftaran.HomePendaftaranViewModel
import com.example.projectakhir.ui.ViewModel.Pendaftaran.InsertPendaftaranViewModel
import com.example.projectakhir.ui.ViewModel.Pendaftaran.UpdatePendaftaranViewModel
import com.example.projectakhir.ui.ViewModel.Siswa.DetailSiswaViewModel
import com.example.projectakhir.ui.ViewModel.Siswa.HomeSiswaViewModel
import com.example.projectakhir.ui.ViewModel.Siswa.InsertSiswaViewModel
import com.example.projectakhir.ui.ViewModel.Siswa.UpdateSiswaViewModel

object PenyediaViewModel {

    val Factory = viewModelFactory {
        initializer { HomeKursusViewModel(kursusAplikasi().container.kurReposirory) }
        initializer { InsertKursusViewModel(kursusAplikasi().container.kurReposirory) }
        initializer { DetailKursusViewModel(createSavedStateHandle(), kursusAplikasi().container.kurReposirory) }
        initializer { UpdateKursusViewModel(createSavedStateHandle(), kursusAplikasi().container.kurReposirory) }

        initializer { HomeInstrukturViewModel(kursusAplikasi().container.instrukturRepository) }
        initializer { InsertInstrukturViewModel(kursusAplikasi().container.instrukturRepository) }
        initializer { DetailInstrukturViewModel(createSavedStateHandle(), kursusAplikasi().container.instrukturRepository) }
        initializer { UpdateInstrukturViewModel(createSavedStateHandle(), kursusAplikasi().container.instrukturRepository) }


        initializer { HomePendaftaranViewModel(kursusAplikasi().container.pendaftaranRepository) }
        initializer { InsertPendaftaranViewModel(kursusAplikasi().container.pendaftaranRepository) }
        initializer { DetailPendaftaranViewModel(createSavedStateHandle(), kursusAplikasi().container.pendaftaranRepository) }
        initializer { UpdatePendaftaranViewModel(createSavedStateHandle(), kursusAplikasi().container.pendaftaranRepository) }

        initializer { HomeSiswaViewModel(kursusAplikasi().container.siswaRepository) }
        initializer { InsertSiswaViewModel(kursusAplikasi().container.siswaRepository) }
        initializer { DetailSiswaViewModel(createSavedStateHandle(), kursusAplikasi().container.siswaRepository) }
        initializer { UpdateSiswaViewModel(createSavedStateHandle(), kursusAplikasi().container.siswaRepository) }
    }
}

fun CreationExtras.kursusAplikasi(): KursusApplications =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KursusApplications)

