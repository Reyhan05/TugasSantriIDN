package com.haidev.tugassantriidn.main.submain.history.models

import com.google.gson.annotations.SerializedName


data class HistoryModel(
    @SerializedName("records")
    val records: List<RecordHistory>
)

data class RecordHistory(
    @SerializedName("isdhuha")
    val isdhuha: Boolean,
    @SerializedName("isorangtua")
    val isorangtua: Boolean,
    @SerializedName("isquran")
    val isquran: Boolean,
    @SerializedName("issholat")
    val issholat: Boolean,
    @SerializedName("istarawih")
    val istarawih: Boolean,
    @SerializedName("kelas")
    val kelas: String,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("tanggal")
    val tanggal: String,
    @SerializedName("waktu_laporan")
    val waktuLaporan: String
)