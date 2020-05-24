package com.haidev.tugassantriidn.main.submain.history.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.haidev.tugassantriidn.R
import com.haidev.tugassantriidn.databinding.FragmentHistoryBinding
import com.haidev.tugassantriidn.main.submain.history.adapters.ItemHistoryAdapter
import com.haidev.tugassantriidn.main.submain.history.models.HistoryModel
import com.haidev.tugassantriidn.main.submain.history.models.RecordHistory
import com.haidev.tugassantriidn.main.submain.history.viewmodels.HistoryViewModel
import com.haidev.tugassantriidn.preferences.UserModel
import com.haidev.tugassantriidn.preferences.UserPreference
import de.mateware.snacky.Snacky


class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var viewModel: HistoryViewModel

    private lateinit var adapterRecycler: ItemHistoryAdapter
    private var listHistory: MutableList<RecordHistory> = mutableListOf()

    private lateinit var mUserPreference: UserPreference
    private lateinit var userModel: UserModel

    var dialogLoad: android.app.AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)
        viewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
        binding.viewmodel = viewModel

        mUserPreference = UserPreference(context!!)
        userModel = mUserPreference.getPref()

        getData()
        viewModel.successResponse.observe(viewLifecycleOwner, Observer {
            onDataGetSuccess(it)
        })

        viewModel.errorHandler.observe(viewLifecycleOwner, Observer {
            onDataGetFailed(it)
        })

        initRecyclerView()
        return binding.root
    }

    private fun getData() {
        val builder = android.app.AlertDialog.Builder(context)
        val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
        val message = dialogView.findViewById<TextView>(R.id.txtProgressBar)
        message.text = "Get Data. . ."
        builder.setView(dialogView)
        builder.setCancelable(false)
        dialogLoad = builder.create()
        dialogLoad!!.show()

        userModel.nama?.let { userModel.kelas?.let { it1 -> viewModel.getData(it, it1) } }
    }

    private fun initRecyclerView() {
        val lManager = LinearLayoutManager(context)
        binding.rvTugas.layoutManager = lManager
        binding.rvTugas.setHasFixedSize(true)

    }

    private fun onDataGetSuccess(it: HistoryModel) {
        dialogLoad?.dismiss()
        listHistory.clear()
        listHistory.addAll(it.records.filter { it.nama == userModel.nama && it.kelas == userModel.kelas })
        if (listHistory.size == 0) {
            binding.txtNoData.visibility = View.VISIBLE
        }
        adapterRecycler = ItemHistoryAdapter(context!!, listHistory)
        binding.rvTugas.adapter = adapterRecycler
        adapterRecycler.notifyDataSetChanged()
    }

    private fun onDataGetFailed(it: Throwable) {
        dialogLoad?.dismiss()
        Snacky.builder()
            .setView(view)
            .setActionText("RELOAD")
            .setActionClickListener {
                getData()
            }
            .setText("Failed get data, try again")
            .setDuration(Snacky.LENGTH_INDEFINITE)
            .error()
            .show()
    }

}
