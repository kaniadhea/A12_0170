package com.example.projectakhir.Repository

import com.example.projectakhir.model.Instruktur
import com.example.projectakhir.service.InstrukturService
import java.io.IOException

interface InstrukturRepository {
    suspend fun getInstruktur(): List<Instruktur>

    suspend fun insertInstruktur(instruktur: Instruktur)

    suspend fun deleteInstruktur(id_instruktur: String)

    suspend fun updateInstruktur(id_instruktur: String, instruktur: Instruktur)

    suspend fun getMahasiswabyid_instruktur(id_instruktur: String): Instruktur
}


class NetworkInstrukturRepository(
    private val instrukturApiService : InstrukturService
): InstrukturRepository {
    override suspend fun getInstruktur(): List<Instruktur> =
        instrukturApiService.getAllInstruktur().data

    override suspend fun insertInstruktur(instruktur: Instruktur) {
        instrukturApiService.insertInstruktur(instruktur)
    }

    override suspend fun updateInstruktur(id_instruktur: String, instruktur: Instruktur) {
        instrukturApiService.updateInstruktur(id_instruktur, instruktur)
    }

    override suspend fun deleteInstruktur(id_instruktur: String) {
        try{
            val response = instrukturApiService.deleteInstruktur(id_instruktur)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete mahasiswa. HTTP Status Code: " +
                        "${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e:Exception){
            throw e
        }
    }

    override suspend fun getMahasiswabyid_instruktur(id_instruktur: String): Instruktur {
        return instrukturApiService.getInstrukturbyid_instruktur(id_instruktur).data
    }
}