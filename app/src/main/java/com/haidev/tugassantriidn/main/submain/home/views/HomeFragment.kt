package com.haidev.tugassantriidn.main.submain.home.views

import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.haidev.tugassantriidn.R
import com.haidev.tugassantriidn.databinding.FragmentHomeBinding
import com.haidev.tugassantriidn.main.submain.home.models.HomeModel
import com.haidev.tugassantriidn.main.submain.home.viewmodels.HomeViewModel
import com.haidev.tugassantriidn.preferences.UserModel
import com.haidev.tugassantriidn.preferences.UserPreference
import de.mateware.snacky.Snacky
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    private lateinit var mUserPreference: UserPreference
    private lateinit var userModel: UserModel

    private lateinit var date: String
    var dialogPost: android.app.AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.viewmodel = viewModel

        date = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
            current.format(formatter)
        } else {
            val dateNow = Calendar.getInstance()
            val formatter = SimpleDateFormat("dd MMMM yyyy")
            formatter.format(dateNow.time)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.successResponse.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            onDataSuccess(it)
        })

        viewModel.errorHandler.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            onDataFailed(it)
        })

        mUserPreference = UserPreference(context!!)
        userModel = mUserPreference.getPref()
        //Toast.makeText(context, "${userModel.tanggalNow} & ${userModel.tanggalYesterday}", Toast.LENGTH_LONG).show()
        initToggle()
        initCekData()

        binding.tvName.text = "Ahlan wa Sahlan, ${userModel.nama}"
        binding.tvTanggal.text = "Ini jadwal hari ini, ${userModel.tanggalNow}"

        binding.btnToggleSholat.setOnToggleChanged {
            if (!it) {
                userModel.issholat = "false"
            } else {
                userModel.issholat = "true"
            }
            mUserPreference.setPref(userModel)
        }

        binding.btnToggleQuran.setOnToggleChanged {
            if (!it) {
                userModel.isquran = "false"
            } else {
                userModel.isquran = "true"
            }
            mUserPreference.setPref(userModel)
        }

        binding.btnToggleDhuha.setOnToggleChanged {
            if (!it) {
                userModel.isdhuha = "false"
            } else {
                userModel.isdhuha = "true"
            }
            mUserPreference.setPref(userModel)
        }

        binding.btnToggleTahajjud.setOnToggleChanged {
            if (!it) {
                userModel.istarawih = "false"
            } else {
                userModel.istarawih = "true"
            }
            mUserPreference.setPref(userModel)
        }

        binding.btnToggleOrangTua.setOnToggleChanged {
            if (!it) {
                userModel.isorangtua = "false"
            } else {
                userModel.isorangtua = "true"
            }
            mUserPreference.setPref(userModel)
        }

        binding.btnLaporkan.setOnClickListener {
            val dialogClickListener = DialogInterface.OnClickListener { dialog, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        val builder = android.app.AlertDialog.Builder(context)
                        val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
                        val message = dialogView.findViewById<TextView>(R.id.txtProgressBar)
                        message.text = "Post Data. . ."
                        builder.setView(dialogView)
                        builder.setCancelable(false)
                        dialogPost = builder.create()
                        dialogPost!!.show()
                        viewModel.postData(
                            userModel.tanggalNow.toString(),
                            userModel.nama.toString(),
                            userModel.kelas.toString(),
                            userModel.issholat.toString(),
                            userModel.isquran.toString(),
                            userModel.isdhuha.toString(),
                            userModel.istarawih.toString(),
                            userModel.isorangtua.toString()
                        )
                        binding.btnLaporkan.isEnabled = false
                    }

                    DialogInterface.BUTTON_NEGATIVE -> {
                        binding.btnLaporkan.isEnabled = true
                        dialog.dismiss()
                    }
                }
            }

            val builder = AlertDialog.Builder(context!!)
            builder.setMessage("Antum sudah yakin?").setPositiveButton("Iya", dialogClickListener)
                .setNegativeButton("Tidak", dialogClickListener).show()
        }
    }

    private fun initCekData() {
        userModel = mUserPreference.getPref()
        if (userModel.tanggalNow == userModel.tanggalYesterday) {
            binding.btnLaporkan.isEnabled = false
            binding.btnLaporkan.text = "Sudah laporan, kembali lagi besok"
        } else {
            binding.btnLaporkan.isEnabled = true
            binding.btnLaporkan.text = "Kirim Laporan"
        }
    }

    private fun onDataSuccess(it: HomeModel) {
        dialogPost?.dismiss()
        Snacky.builder()
            .setView(view)
            .setText("Laporan data sukses terkirim")
            .setDuration(Snacky.LENGTH_SHORT)
            .success()
            .show()
        binding.btnLaporkan.isEnabled = false
        binding.btnLaporkan.text = "Sudah laporan, kembali lagi besok"
        resetPref()
        initToggle()
        userModel.tanggalYesterday = date
        mUserPreference.setPref(userModel)
    }

    private fun onDataFailed(it: Throwable) {
        dialogPost?.dismiss()
        Snacky.builder()
            .setView(view)
            .setText("Laporan data gagal, coba ulangi")
            .setDuration(Snacky.LENGTH_SHORT)
            .error()
            .show()
        binding.btnLaporkan.isEnabled = true
    }

    private fun resetPref() {
        userModel.issholat = "false"
        userModel.isquran = "false"
        userModel.isdhuha = "false"
        userModel.istarawih = "false"
        userModel.isorangtua = "false"
        mUserPreference.setPref(userModel)
    }

    private fun initToggle() {

        if (userModel.issholat != "true") {
            userModel.issholat = "false"
            binding.btnToggleSholat.setToggleOff()
        } else {
            binding.btnToggleSholat.setToggleOn()
        }

        if (userModel.isquran != "true") {
            userModel.isquran = "false"
            binding.btnToggleQuran.setToggleOff()
        } else {
            binding.btnToggleQuran.setToggleOn()
        }

        if (userModel.isdhuha != "true") {
            userModel.isdhuha = "false"
            binding.btnToggleDhuha.setToggleOff()
        } else {
            binding.btnToggleDhuha.setToggleOn()
        }

        if (userModel.istarawih != "true") {
            userModel.istarawih = "false"
            binding.btnToggleTahajjud.setToggleOff()
        } else {
            binding.btnToggleTahajjud.setToggleOn()
        }

        if (userModel.isorangtua != "true") {
            userModel.isorangtua = "false"
            binding.btnToggleOrangTua.setToggleOff()
        } else {
            binding.btnToggleOrangTua.setToggleOn()
        }
    }

}
