package com.haidev.tugassantriidn.main.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.haidev.tugassantriidn.R
import com.haidev.tugassantriidn.preferences.UserModel
import com.haidev.tugassantriidn.preferences.UserPreference
import de.mateware.snacky.Snacky
import kotlinx.android.synthetic.main.bottomsheet_kelas.view.*
import kotlinx.android.synthetic.main.fragment_intro.*

class IntroFragment : Fragment() {
    private lateinit var mUserPreference: UserPreference
    private lateinit var userModel: UserModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mUserPreference = UserPreference(context!!)
        userModel = mUserPreference.getPref()

        etKelas.setOnClickListener {
            val viewDialog = layoutInflater.inflate(R.layout.bottomsheet_kelas, null)
            val dialog = BottomSheetDialog(context!!, R.style.BaseBottomSheetDialog)
            viewDialog.close.setOnClickListener {
                dialog.dismiss()
            }

            val data =
                arrayOf(
                    "7 A",
                    "7 B",
                    "7 C",
                    "7 D",
                    "8 A",
                    "8 B",
                    "9 A",
                    "10 TKJ A",
                    "10 TKJ B",
                    "10 RPL A",
                    "10 RPL B",
                    "10 RPL C",
                    "11 RPL A",
                    "11 RPL B",
                    "11 TKJ A",
                    "11 TKJ B",
                    "12 RPL A",
                    "12 TKJ A"
                )
            viewDialog.numberPicker.minValue = 1
            viewDialog.numberPicker.maxValue = data.size
            viewDialog.numberPicker.displayedValues = data
            viewDialog.numberPicker.value = data.size

            viewDialog.btnTerapkan.setOnClickListener {
                dialog.dismiss()
                etKelas.text = data[viewDialog.numberPicker.value - 1]
            }
            dialog.setContentView(viewDialog)
            dialog.show()
        }

        btnLanjutkan.setOnClickListener {
            when {
                etNama.text.toString().isEmpty() -> {
                    Snacky.builder()
                        .setView(view)
                        .setText("Isi Nama terlebih dahulu!")
                        .setDuration(Snacky.LENGTH_SHORT)
                        .warning()
                        .show()
                }
                etKelas.text.toString().isEmpty() -> {
                    Snacky.builder()
                        .setView(view)
                        .setText("Isi Kelas terlebih dahulu!")
                        .setDuration(Snacky.LENGTH_SHORT)
                        .warning()
                        .show()
                }
                else -> {
                    userModel.nama = etNama.text.toString()
                    userModel.kelas = etKelas.text.toString()
                    mUserPreference.setPref(userModel)
                    Navigation.findNavController(view)
                        .navigate(R.id.action_introFragment_to_subMainFragment, null)
                }
            }
        }
    }
}
