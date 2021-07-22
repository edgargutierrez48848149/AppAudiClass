package com.dvalic.appaudiclass.ui.main.fragments.buys

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.dvalic.appaudiclass.R
import com.dvalic.appaudiclass.databinding.FragmentAgenciesBinding

class AgenciesFragment : Fragment(R.layout.fragment_agencies) {

    private lateinit var binding: FragmentAgenciesBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAgenciesBinding.bind(view)
    }

}