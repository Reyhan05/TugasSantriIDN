package com.haidev.tugassantriidn.networks

import com.haidev.tugassantriidn.main.submain.history.models.HistoryModel
import com.haidev.tugassantriidn.main.submain.home.models.HomeModel
import io.reactivex.Observable
import retrofit2.http.*

interface RestApi {
    @POST("exec")
    @FormUrlEncoded
    fun postData(
        @Field("tanggal") tanggal: String,
        @Field("nama") nama: String,
        @Field("kelas") kelas: String,
        @Field("issholat") issholat: String,
        @Field("isquran") isquran: String,
        @Field("isdhuha") isdhuha: String,
        @Field("istarawih") istarawih: String,
        @Field("isorangtua") isorangtua: String
    ): Observable<HomeModel>

    @GET("exec?action=readData")
    fun getData(
        @Query("nama") nama: String,
        @Query("kelas") kelas: String
    ): Observable<HistoryModel>
}