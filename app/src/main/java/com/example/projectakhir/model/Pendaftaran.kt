package com.example.projectakhir.model



import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class AllPendaftaranRespons (
    val status: Boolean,
    val message: String,
    val data: List<Pendaftaran>
)

@Serializable
data class PendaftaranDetailRespons (
    val status: Boolean,
    val message: String,
    val data: Pendaftaran
)


@Serializable
data class Pendaftaran(
    val id_pendaftaran: Int,
    val id_kursus: Int,
    val id_siswa: Int,
    val tanggal_pendaftaran: String
)

