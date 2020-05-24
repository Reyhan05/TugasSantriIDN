package com.haidev.tugassantriidn.main.submain.history.viewmodels

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.haidev.tugassantriidn.main.submain.history.models.HistoryModel
import com.haidev.tugassantriidn.networks.repositories.MainRepository

class HistoryViewModel(application: Application) : AndroidViewModel(application) {
    var isLoading: ObservableField<Boolean> = ObservableField()
    var successResponse: MutableLiveData<HistoryModel> = MutableLiveData()
    var errorHandler: MutableLiveData<Throwable> = MutableLiveData()

    private var mainRepository = MainRepository()

    fun getData(nama: String, kelas: String) {
        isLoading.set(true)
        mainRepository.getData({
            isLoading.set(false)
            successResponse.postValue(it)
        }, {
            isLoading.set(false)
            errorHandler.postValue(it)
        },
            nama,
            kelas
        )
    }


    override fun onCleared() {
        super.onCleared()
        mainRepository.cleared()
    }
}