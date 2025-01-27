package com.example.projectakhir.service

import com.example.projectakhir.model.AllKursusRespons
import com.example.projectakhir.model.Kursus
import com.example.projectakhir.model.KursusDetailRespons
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.Response

interface KursusService {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @GET("kursus")
    suspend fun getAllKursus(): AllKursusRespons

    @GET("kursus/{id_kursus}")
    suspend fun getKursusbyid_kursus(@Path("id_kursus") id_kursus: Int): KursusDetailRespons

    @POST("kursus/store")
    suspend fun insertKursus(@Body kursus: Kursus): Response<Unit>

    @PUT("kursus/{id_kursus}")
    suspend fun updateKursus(
        @Path("id_kursus") id_kursus: Int,
        @Body kursus: Kursus
    ): Response<Unit>

    @DELETE("kursus/{id_kursus}")
    suspend fun deleteKursus(@Path("id_kursus") id_kursus: Int): Response<Unit>
}
