package com.dvalic.appaudiclass.ui.main.fragments.us

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dvalic.appaudiclass.R
import com.dvalic.appaudiclass.databinding.FragmentUsCompanyBinding

class UsCompanyFragment : Fragment(R.layout.fragment_us_company) {

    private lateinit var binding: FragmentUsCompanyBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUsCompanyBinding.bind(view)
    }
}