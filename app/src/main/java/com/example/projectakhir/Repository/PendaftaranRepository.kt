package com.example.projectakhir.Repository

import com.example.projectakhir.model.AllKursusRespons
import com.example.projectakhir.model.AllPendaftaranRespons
import com.example.projectakhir.model.Pendaftaran
import com.example.projectakhir.model.Siswa
import com.example.projectakhir.service.PendaftaranService
import com.example.projectakhir.service.SiswaService


interface PendaftaranRepository {
    suspend fun getAllPendaftaran(): AllPendaftaranRespons
    suspend fun insertPendaftaran(pendaftaran: Pendaftaran)
    suspend fun updatePendaftaran(id_pendaftaran: Int, pendaftaran: Pendaftaran)
    suspend fun deletePendaftaran(id_pendaftaran: Int)
    suspend fun getPendaftaranbyid_pendaftaran(id_pendaftaran: Int): Pendaftaran
}

class NetworkPendaftaranRepository(
    private val pendaftaranApiService: PendaftaranService
): PendaftaranRepository {

    override suspend fun getAllPendaftaran(): AllPendaftaranRespons =
        pendaftaranApiService.getAllPendaftaran()

    override suspend fun insertPendaftaran(pendaftaran: Pendaftaran) {
        pendaftaranApiService.insertPendaftaran(pendaftaran)
    }

    override suspend fun updatePendaftaran(id_pendaftaran: Int, pendaftaran: Pendaftaran) {
        pendaftaranApiService.updatePendaftaran(id_pendaftaran, pendaftaran)
    }

    override suspend fun deletePendaftaran(id_pendaftaran: Int) {
        try {
            // Kirimkan id_pendaftaran sebagai Int
            val response = pendaftaranApiService.deletePendaftaran(id_pendaftaran)
            if (!response.isSuccessful) {
                throw java.io.IOException(
                    "Failed to delete pendaftaran. HTTP Status Code: " +
                            "${response.code()}"
                )
            } else {
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getPendaftaranbyid_pendaftaran(id_pendaftaran: Int): Pendaftaran {
        // Kembalikan Pendaftaran menggunakan id_pendaftaran yang bertipe Int
        return pendaftaranApiService.getPendaftaranbyid_pendaftaran(id_pendaftaran).data
    }
}
