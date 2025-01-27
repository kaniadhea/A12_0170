package com.example.projectakhir.model

import kotlinx.serialization.Serializable

@Serializable
data class AllKursusRespons (
    val status: Boolean,
    val message: String,
    val data: List<Kursus>
)

@Serializable
data class KursusDetailRespons(
    val status: Boolean,
    val message: String,
    val data: Kursus
)

@Serializable
data class Kursus(
    val id_kursus: Int,
    val nama_kursus: String,
    val deskripsi: String,
    val kategori: String,
    val harga: String,
    val id_instruktur: Int
)
