package com.example.projectakhir.container

import com.example.projectakhir.Repository.InstrukturRepository
import com.example.projectakhir.Repository.NetworkInstrukturRepository
import com.example.projectakhir.Repository.NetworkPendaftaranRepository
import com.example.projectakhir.Repository.NetworkSiswaReposiroty
import com.example.projectakhir.Repository.PendaftaranRepository
import com.example.projectakhir.Repository.SiswaRepository
import com.example.projectakhir.repository.KursusRepository
import com.example.projectakhir.repository.NetworkKursusRepository
import com.example.projectakhir.service.InstrukturService
import com.example.projectakhir.service.KursusService
import com.example.projectakhir.service.PendaftaranService
import com.example.projectakhir.service.SiswaService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer{
    val instrukturRepository:InstrukturRepository;
    val kurReposirory: KursusRepository;
    val pendaftaranRepository: PendaftaranRepository;
    val siswaRepository: SiswaRepository
}

class KursusContainer : AppContainer {

    private val baseUrl= "http://10.0.2.2:3000/api/"
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()



    private val siswaService: SiswaService by  lazy {
        retrofit.create(SiswaService::class.java)
    }
    override val siswaRepository: SiswaRepository by lazy {
        NetworkSiswaReposiroty(siswaService)
    }

    private val pendaftaranService: PendaftaranService by  lazy {
        retrofit.create(PendaftaranService::class.java)
    }
    override val pendaftaranRepository: PendaftaranRepository by lazy {
        NetworkPendaftaranRepository(pendaftaranService)
    }

    private val kursusService: KursusService by  lazy {
        retrofit.create(KursusService::class.java)
    }
    override val kurReposirory: KursusRepository by lazy {
        NetworkKursusRepository(kursusService)
    }

    private val instrukturService: InstrukturService by  lazy {
        retrofit.create(InstrukturService::class.java)
    }
    override val instrukturRepository: InstrukturRepository by lazy {
        NetworkInstrukturRepository(instrukturService)
    }
}