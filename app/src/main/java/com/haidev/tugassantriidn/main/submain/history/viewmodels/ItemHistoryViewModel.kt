package com.haidev.tugassantriidn.main.submain.history.viewmodels

import android.content.Context
import androidx.databinding.ObservableField
import com.haidev.tugassantriidn.R
import com.haidev.tugassantriidn.databinding.ItemHistoryBinding
import com.haidev.tugassantriidn.main.submain.history.models.RecordHistory


class ItemHistoryViewModel(
    model: RecordHistory,
    binding: ItemHistoryBinding,
    context: Context
) {

    init {
        //Sholat
        if (model.issholat) {
            binding.tvSholat.setCompoundDrawablesWithIntrinsicBounds(
                context.getDrawable(R.drawable.ic_check_circle),
                null,
                null,
                null
            )
        } else {
            binding.tvSholat.setCompoundDrawablesWithIntrinsicBounds(
                context.getDrawable(R.drawable.ic_close_circle),
                null,
                null,
                null
            )
        }

        //Quran
        if (model.isquran) {
            binding.tvQuran.setCompoundDrawablesWithIntrinsicBounds(
                context.getDrawable(R.drawable.ic_check_circle),
                null,
                null,
                null
            )
        } else {
            binding.tvQuran.setCompoundDrawablesWithIntrinsicBounds(
                context.getDrawable(R.drawable.ic_close_circle),
                null,
                null,
                null
            )
        }

        //Dhuha
        if (model.isdhuha) {
            binding.tvDhuha.setCompoundDrawablesWithIntrinsicBounds(
                context.getDrawable(R.drawable.ic_check_circle),
                null,
                null,
                null
            )
        } else {
            binding.tvDhuha.setCompoundDrawablesWithIntrinsicBounds(
                context.getDrawable(R.drawable.ic_close_circle),
                null,
                null,
                null
            )
        }

        //Tahajjud
        if (model.istarawih) {
            binding.tvTarawih.setCompoundDrawablesWithIntrinsicBounds(
                context.getDrawable(R.drawable.ic_check_circle),
                null,
                null,
                null
            )
        } else {
            binding.tvTarawih.setCompoundDrawablesWithIntrinsicBounds(
                context.getDrawable(R.drawable.ic_close_circle),
                null,
                null,
                null
            )
        }

        //Ortu
        if (model.isorangtua) {
            binding.tvOrtu.setCompoundDrawablesWithIntrinsicBounds(
                context.getDrawable(R.drawable.ic_check_circle),
                null,
                null,
                null
            )
        } else {
            binding.tvOrtu.setCompoundDrawablesWithIntrinsicBounds(
                context.getDrawable(R.drawable.ic_close_circle),
                null,
                null,
                null
            )
        }
    }

    var tanggal: ObservableField<String?> = ObservableField(model.waktuLaporan)
}