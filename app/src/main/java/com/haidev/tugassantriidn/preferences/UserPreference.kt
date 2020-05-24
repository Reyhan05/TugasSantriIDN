package com.haidev.tugassantriidn.preferences

import android.content.Context

internal class UserPreference(context: Context) {
    companion object {
        private const val PREFS_NAME = "user_pref"
        private const val NAMA = "nama"
        private const val KELAS = "kelas"
        private const val TANGGALNOW = "tanggalnow"
        private const val TANGGALYESTERDAY = "tanggalyesterday"
        private const val ISSHOLAT = "issholat"
        private const val ISQURAN = "isquran"
        private const val ISDHUHA = "isdhuha"
        private const val ISTARAWIH = "istarawih"
        private const val ISORANGTUA = "isorangtua"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    fun setPref(value: UserModel) {
        val editor = preferences.edit()
        editor.putString(NAMA, value.nama)
        editor.putString(KELAS, value.kelas)
        editor.putString(TANGGALNOW, value.tanggalNow)
        editor.putString(TANGGALYESTERDAY, value.tanggalYesterday)
        editor.putString(ISSHOLAT, value.issholat)
        editor.putString(ISQURAN, value.isquran)
        editor.putString(ISDHUHA, value.isdhuha)
        editor.putString(ISTARAWIH, value.istarawih)
        editor.putString(ISORANGTUA, value.isorangtua)
        editor.apply()
    }

    fun getPref(): UserModel {
        val model = UserModel()
        model.nama = preferences.getString(NAMA, "")
        model.kelas = preferences.getString(KELAS, "")
        model.tanggalNow = preferences.getString(TANGGALNOW, "")
        model.tanggalYesterday = preferences.getString(TANGGALYESTERDAY, "")
        model.issholat = preferences.getString(ISSHOLAT, "")
        model.isquran = preferences.getString(ISQURAN, "")
        model.isdhuha = preferences.getString(ISDHUHA, "")
        model.istarawih = preferences.getString(ISTARAWIH, "")
        model.isorangtua = preferences.getString(ISORANGTUA, "")
        return model
    }
}