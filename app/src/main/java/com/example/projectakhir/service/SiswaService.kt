package com.example.projectakhir.service

import com.example.projectakhir.model.AllSiswaRespons
import com.example.projectakhir.model.Siswa
import com.example.projectakhir.model.SiswaDetailRespons
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query
import retrofit2.Response
import retrofit2.http.Path

interface SiswaService {

    @Headers(
        "Accept: application/json",
        "Content-Type: apllication/json",
    )

    //@GET("bacamahasiswa.php")
    @GET ("siswa")
    suspend fun getAllSiswa(): AllSiswaRespons

    //@GET("baca1mahasiswa.php/{nim}")
    @GET("siswa/{id_siswa}")
    suspend fun getSiswabyid_siswa(@Path("id_siswa") id_siswa:Int): SiswaDetailRespons

    @POST("siswa/store")
    suspend fun insertSiswa(@Body siswa: Siswa)

    //@PUT("editmahasiswa.php/{nim}")
    @PUT("siswa/{id_siswa}")
    suspend fun updateSiswa(@Path("id_siswa")id_siswa: Int, @Body siswa: Siswa)

    //@DELETE("deletemahasiswa.php/{nim}")
    @DELETE("siswa/{id_siswa}")
    suspend fun deleteSiswa(@Path("id_siswa")id_siswa: Int): Response<Void>

}