package com.haidev.tugassantriidn.networks.repositories

import com.haidev.tugassantriidn.main.submain.history.models.HistoryModel
import com.haidev.tugassantriidn.main.submain.home.models.HomeModel
import com.haidev.tugassantriidn.networks.ApiObserver
import com.haidev.tugassantriidn.networks.ServiceFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainRepository {
    private val compositeDisposable = CompositeDisposable()
    private val apiService = ServiceFactory.create()

    fun postData(
        onResult: (HomeModel) -> Unit,
        onError: (Throwable) -> Unit,
        tanggal: String,
        nama: String,
        kelas: String,
        issholat: String,
        isquran: String,
        isdhuha: String,
        istarawih: String,
        isorangtua: String
    ) {
        apiService.postData(tanggal, nama, kelas, issholat, isquran, isdhuha, istarawih, isorangtua)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<HomeModel>(compositeDisposable) {
                override fun onApiSuccess(data: HomeModel) {
                    onResult(data)
                }

                override fun onApiError(er: Throwable) {
                    onError(er)
                }
            })
    }

    fun getData(
        onResult: (HistoryModel) -> Unit,
        onError: (Throwable) -> Unit,
        nama: String,
        kelas: String
    ) {
        apiService.getData(nama, kelas)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<HistoryModel>(compositeDisposable) {
                override fun onApiSuccess(data: HistoryModel) {
                    onResult(data)
                }

                override fun onApiError(er: Throwable) {
                    onError(er)
                }
            })
    }

    fun cleared() {
        compositeDisposable.clear()
    }
}