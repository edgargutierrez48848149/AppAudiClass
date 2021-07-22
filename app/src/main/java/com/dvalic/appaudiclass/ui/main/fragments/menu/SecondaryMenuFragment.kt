package com.dvalic.appaudiclass.ui.main.fragments.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.dvalic.appaudiclass.R
import com.dvalic.appaudiclass.databinding.FragmentSecondaryMenuBinding

class SecondaryMenuFragment : Fragment(R.layout.fragment_secondary_menu) {

    private lateinit var binding: FragmentSecondaryMenuBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSecondaryMenuBinding.bind(view)
    }
}