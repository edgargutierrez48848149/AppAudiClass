package com.dvalic.appaudiclass.ui.main.fragments.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.dvalic.appaudiclass.R
import com.dvalic.appaudiclass.databinding.FragmentMainMenuBinding

class MainMenuFragment : Fragment(R.layout.fragment_main_menu) {

    private lateinit var binding: FragmentMainMenuBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainMenuBinding.bind(view)
    }
}