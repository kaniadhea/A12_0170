package com.example.projectakhir.repository

import com.example.projectakhir.model.AllKursusRespons
import com.example.projectakhir.model.Kursus
import com.example.projectakhir.service.KursusService
import java.io.IOException

interface KursusRepository {
    suspend fun getAllKursus(): AllKursusRespons
    suspend fun insertKursus(kursus: Kursus)
    suspend fun updateKursus(id_kursus: Int, kursus: Kursus)
    suspend fun deleteKursus(id_kursus: Int)
    suspend fun getKursusbyid_kursus(id_kursus: Int): Kursus
}

class NetworkKursusRepository(
    private val kursusApiService: KursusService
): KursusRepository {

    override suspend fun getAllKursus(): AllKursusRespons =
        kursusApiService.getAllKursus()

    override suspend fun insertKursus(kursus: Kursus) {
        kursusApiService.insertKursus(kursus)
    }

    override suspend fun updateKursus(id_kursus: Int, kursus: Kursus) {
        kursusApiService.updateKursus(id_kursus, kursus)
    }

    override suspend fun deleteKursus(id_kursus: Int) {
        try {
            val response = kursusApiService.deleteKursus(id_kursus)
            if (!response.isSuccessful) {
                throw IOException(
                    "Failed to delete kursus. HTTP Status Code: " +
                            "${response.code()}"
                )
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getKursusbyid_kursus(id_kursus: Int): Kursus {
        return kursusApiService.getKursusbyid_kursus(id_kursus).data
    }
}
