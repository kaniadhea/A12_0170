package com.example.projectakhir.service


import com.example.projectakhir.model.AllInstrukturRespons
import com.example.projectakhir.model.Instruktur
import com.example.projectakhir.model.InstrukturDetailRespons
import retrofit2.Response

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface InstrukturService {

    @Headers(
        "Accept: application/json",
        "Content-Type: apllication/json",
    )

    //@GET("bacamahasiswa.php")
    @GET ("instruktur")
    suspend fun getAllInstruktur():AllInstrukturRespons

    //@GET("baca1mahasiswa.php/{id}")
    @GET("instruktur/{id_instruktur}")
    suspend fun getInstrukturbyid_instruktur(@Path("id_instruktur") id_instruktur:Int): InstrukturDetailRespons

    @POST("instruktur/store")
    suspend fun insertInstruktur(@Body instruktur: Instruktur)

    //@PUT("editmahasiswa.php/{id}")
    @PUT("instruktur/{id_instruktur}")
    suspend fun updateInstruktur(@Path("id_instruktur")id_instruktur: Int, @Body instruktur: Instruktur)

    //@DELETE("deletemahasiswa.php/{id}")
    @DELETE("instruktur/{id_instruktur}")
    suspend fun deleteInstruktur(@Path("id_instruktur")id_instruktur: Int): Response<Void>
}