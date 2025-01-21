package com.example.projectakhir.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class AllInstrukturResponse (
    val status: Boolean,
    val message: String,
    val data: List<Instruktur>
)

@Serializable
data class InstrukturDetailRespons (
    val status: Boolean,
    val message: String,
    val data: Instruktur
)

@Serializable
data class Instruktur (
    val id_instruktur: String,
    val nama_instruktur: String,
    val email: String,
    val nomor_telepon: String,
    val deskripsi:String
)