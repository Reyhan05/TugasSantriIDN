package com.haidev.tugassantriidn.preferences

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class UserModel(
    var nama: String? = null,
    var kelas: String? = null,
    var tanggalNow: String? = null,
    var tanggalYesterday: String? = null,
    var issholat: String? = null,
    var isquran: String? = null,
    var isdhuha: String? = null,
    var istarawih: String? = null,
    var isorangtua: String? = null
) : Parcelable