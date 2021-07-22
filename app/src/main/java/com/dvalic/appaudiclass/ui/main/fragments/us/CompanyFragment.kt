package com.dvalic.appaudiclass.ui.main.fragments.us

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dvalic.appaudiclass.R
import com.dvalic.appaudiclass.databinding.FragmentCompanyBinding
import com.dvalic.appaudiclass.databinding.FragmentSecondaryMenuBinding

class CompanyFragment : Fragment(R.layout.fragment_company) {

    private lateinit var binding: FragmentCompanyBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCompanyBinding.bind(view)
    }
}