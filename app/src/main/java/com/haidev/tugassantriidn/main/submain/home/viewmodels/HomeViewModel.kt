package com.haidev.tugassantriidn.main.submain.home.viewmodels

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.haidev.tugassantriidn.main.submain.home.models.HomeModel
import com.haidev.tugassantriidn.networks.repositories.MainRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    var isLoading: ObservableField<Boolean> = ObservableField()
    var successResponse: MutableLiveData<HomeModel> = MutableLiveData()
    var errorHandler: MutableLiveData<Throwable> = MutableLiveData()

    private var mainRepository = MainRepository()

    fun postData(
        tanggal: String,
        nama: String,
        kelas: String,
        issholat: String,
        isquran: String,
        isdhuha: String,
        istarawih: String,
        isorangtua: String
    ) {
        isLoading.set(true)
        mainRepository.postData({
            isLoading.set(false)
            successResponse.postValue(it)
        }, {
            isLoading.set(false)
            errorHandler.postValue(it)
        },
            tanggal, nama, kelas, issholat, isquran, isdhuha, istarawih, isorangtua
        )
    }


    override fun onCleared() {
        super.onCleared()
        mainRepository.cleared()
    }
}