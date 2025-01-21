package com.example.projectakhir.container


import com.example.projectakhir.Repository.InstrukturRepository
import com.example.projectakhir.Repository.NetworkInstrukturRepository
import com.example.projectakhir.service.InstrukturService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer{
    val instrukturRepository:InstrukturRepository
}

class InstrukturContainer : AppContainer {

    private val baseUrl= "http://10.0.2.2:3000/api/instruktur/"
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val instrukturService: InstrukturService by  lazy {
        retrofit.create(InstrukturService::class.java)
    }

    override val instrukturRepository: InstrukturRepository by lazy {
        NetworkInstrukturRepository(instrukturService)
    }
}