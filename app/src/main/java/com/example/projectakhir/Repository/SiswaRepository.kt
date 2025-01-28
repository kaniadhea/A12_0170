package com.example.projectakhir.Repository

import com.example.projectakhir.model.AllKursusRespons
import com.example.projectakhir.model.AllSiswaRespons
import com.example.projectakhir.model.Siswa
import com.example.projectakhir.service.SiswaService


interface SiswaRepository {
    suspend fun getAllSiswa(): AllSiswaRespons
    suspend fun insertSiswa(siswa: Siswa)
    suspend fun updateSiswa(id_siswa: Int, siswa: Siswa)
    suspend fun deleteSiswa(id_siswa: Int)
    suspend fun getSiswabyid_siswa(id_siswa: Int): Siswa?

}

class NetworkSiswaReposiroty(
    private val siswaApiService: SiswaService
): SiswaRepository {

    override suspend fun getAllSiswa(): AllSiswaRespons =
        siswaApiService.getAllSiswa()

    override suspend fun insertSiswa(siswa: Siswa) {
        siswaApiService.insertSiswa(siswa)
    }

    override suspend fun updateSiswa(id_siswa: Int, siswa: Siswa) {
        siswaApiService.updateSiswa(id_siswa, siswa)
    }

    override suspend fun deleteSiswa(id_siswa: Int) {
        try {
            val response = siswaApiService.deleteSiswa(id_siswa)
            if (!response.isSuccessful) {
                throw java.io.IOException(
                    "Failed to delete mahasiswa. HTTP Status Code: " +
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

    override suspend fun getSiswabyid_siswa(id_siswa: Int): Siswa {
        return siswaApiService.getSiswabyid_siswa(id_siswa).data
    }
}