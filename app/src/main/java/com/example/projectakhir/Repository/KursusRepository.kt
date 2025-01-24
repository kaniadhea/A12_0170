package com.example.projectakhir.repository

import com.example.projectakhir.model.Kursus
import com.example.projectakhir.service.KursusService
import java.io.IOException

interface KursusRepository {
    suspend fun getKursus(): List<Kursus>
    suspend fun insertKursus(kursus: Kursus)
    suspend fun updateKursus(id_kursus: String, kursus: Kursus)
    suspend fun deleteKursus(id_kursus: String)
    suspend fun getKursusbyid_kursus(id_kursus: String): Kursus
}

class NetworkKursusRepository(
    private val kursusApiService: KursusService
) : KursusRepository {
    override suspend fun getKursus(): List<Kursus> {
        val response = kursusApiService.getAllKursus()
        if (response.isSuccessful) {
            return response.body()?.data ?: emptyList()
        } else {
            throw IOException("Failed to fetch courses. HTTP ${response.code()}: ${response.message()}")
        }
    }

    override suspend fun insertKursus(kursus: Kursus) {
        val response = kursusApiService.insertKursus(kursus)
        if (!response.isSuccessful) {
            throw IOException("Failed to insert course. HTTP ${response.code()}: ${response.message()}")
        }
    }

    override suspend fun updateKursus(id_kursus: String, kursus: Kursus) {
        val response = kursusApiService.updateKursus(id_kursus, kursus)
        if (!response.isSuccessful) {
            throw IOException("Failed to update course. HTTP ${response.code()}: ${response.message()}")
        }
    }

    override suspend fun deleteKursus(id_kursus: String) {
        val response = kursusApiService.deleteKursus(id_kursus)
        if (!response.isSuccessful) {
            throw IOException("Failed to delete course. HTTP ${response.code()}: ${response.message()}")
        }
    }

    override suspend fun getKursusbyid_kursus(id_kursus: String): Kursus {
        val response = kursusApiService.getKursusbyid_kursus(id_kursus)
        if (response.isSuccessful) {
            return response.body()?.data ?: throw IOException("Course not found")
        } else {
            throw IOException("Failed to fetch course details. HTTP ${response.code()}: ${response.message()}")
        }
    }
}
