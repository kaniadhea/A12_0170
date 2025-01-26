package com.example.projectakhir.service

import com.example.projectakhir.model.AllPendaftaranRespons
import com.example.projectakhir.model.Pendaftaran
import com.example.projectakhir.model.PendaftaranDetailRespons
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.Response

interface PendaftaranService {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json", // Fixed typo here, from apllication/json to application/json
    )

    @GET("pendaftaran")
    suspend fun getAllPendaftaran(): AllPendaftaranRespons


    @GET("pendaftaran/{id_pendaftaran}")
    suspend fun getPendaftaranbyid_pendaftaran(@Path("id_pendaftaran") id_pendaftaran: Int): PendaftaranDetailRespons


    @POST("pendaftaran/store")
    suspend fun insertPendaftaran(@Body pendaftaran: Pendaftaran)


    @PUT("pendaftaran/{id_pendaftaran}")
    suspend fun updatePendaftaran(@Path("id_pendaftaran") id_pendaftaran: Int, @Body pendaftaran: Pendaftaran)


    @DELETE("pendaftaran/{id_pendaftaran}")
    suspend fun deletePendaftaran(@Path("id_pendaftaran") id_pendaftaran: Int): Response<Void>
}
