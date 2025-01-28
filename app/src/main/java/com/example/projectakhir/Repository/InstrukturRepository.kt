package com.example.projectakhir.Repository

import com.example.projectakhir.model.AllInstrukturRespons
import com.example.projectakhir.model.Instruktur
import com.example.projectakhir.service.InstrukturService
import java.io.IOException

interface InstrukturRepository {
    suspend fun getAllInstruktur(): AllInstrukturRespons

    suspend fun insertInstruktur(instruktur: Instruktur)

    suspend fun deleteInstruktur(id_instruktur: Int)

    suspend fun updateInstruktur(id_instruktur: Int, instruktur: Instruktur)

    suspend fun getInstrukturbyid_instruktur(id_instruktur: Int): Instruktur
}


class NetworkInstrukturRepository(
    private val instrukturApiService: InstrukturService
) : InstrukturRepository {
    override suspend fun getAllInstruktur(): AllInstrukturRespons =
        instrukturApiService.getAllInstruktur()

    override suspend fun insertInstruktur(instruktur: Instruktur) {
        instrukturApiService.insertInstruktur(instruktur)
    }

    override suspend fun updateInstruktur(id_instruktur: Int, instruktur: Instruktur) {
        instrukturApiService.updateInstruktur(id_instruktur, instruktur)
    }

    // Ubah parameter id_instruktur ke Int
    override suspend fun deleteInstruktur(id_instruktur: Int) {
        try {
            val response = instrukturApiService.deleteInstruktur(id_instruktur)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete instruktur. HTTP Status Code: " +
                        "${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    // Ubah parameter id_instruktur ke Int
    override suspend fun getInstrukturbyid_instruktur(id_instruktur: Int): Instruktur {
        return instrukturApiService.getInstrukturbyid_instruktur(id_instruktur).data
    }
}
