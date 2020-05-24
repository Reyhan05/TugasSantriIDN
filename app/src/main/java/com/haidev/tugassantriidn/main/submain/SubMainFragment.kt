package com.haidev.tugassantriidn.main.submain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.haidev.tugassantriidn.R
import kotlinx.android.synthetic.main.fragment_sub_main.*

class SubMainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sub_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navController = Navigation.findNavController(requireActivity(), R.id.fragmentSubMain)
        NavigationUI.setupWithNavController(navigationView, navController)
    }

}
