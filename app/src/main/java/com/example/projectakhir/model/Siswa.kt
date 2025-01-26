package com.example.projectakhir.model

import android.provider.ContactsContract.CommonDataKinds.Email


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class AllSiswaRespons (
    val status: Boolean,
    val message: String,
    val data: List<Siswa>
)

@Serializable
data class SiswaDetailRespons (
    val status: Boolean,
    val message: String,
    val data: Siswa
)


@Serializable
data class Siswa(
    val id_siswa: Int,
    val email: String,
    val nama_siswa: String,
    val nomor_telepon: String
)

