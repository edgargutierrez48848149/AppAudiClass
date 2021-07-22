package com.dvalic.appaudiclass.ui.main.fragments.buys

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dvalic.appaudiclass.R
import com.dvalic.appaudiclass.databinding.FragmentVersionsBinding

class VersionsFragment : Fragment(R.layout.fragment_versions) {

    private lateinit var binding: FragmentVersionsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentVersionsBinding.bind(view)
    }
}