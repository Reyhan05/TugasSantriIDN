package com.haidev.tugassantriidn.main.splash

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.haidev.tugassantriidn.R
import com.haidev.tugassantriidn.preferences.UserModel
import com.haidev.tugassantriidn.preferences.UserPreference
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class SplashScreenFragment : Fragment() {

    private lateinit var mUserPreference: UserPreference
    private lateinit var userModel: UserModel

    private var date = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mUserPreference = UserPreference(context!!)
        userModel = mUserPreference.getPref()

        date = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
            current.format(formatter)
        } else {
            val dateNow = Calendar.getInstance()
            val formatter = SimpleDateFormat("dd MMMM yyyy")
            formatter.format(dateNow.time)
        }

        Handler().postDelayed({
            if (userModel.nama != "") {
                Navigation.findNavController(view)
                    .navigate(R.id.action_splashScreenFragment_to_subMainFragment, null)
                userModel.tanggalNow = date
                mUserPreference.setPref(userModel)
            } else {
                userModel.tanggalNow = date
                userModel.issholat = "false"
                userModel.isquran = "false"
                userModel.isdhuha = "false"
                userModel.istarawih = "false"
                userModel.isorangtua = "false"
                mUserPreference.setPref(userModel)
                Navigation.findNavController(view)
                    .navigate(R.id.action_splashScreenFragment_to_introFragment, null)
            }

        }, 2000)
    }

}
