package com.dvalic.appaudiclass.ui.main.fragments.buys

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dvalic.appaudiclass.R
import com.dvalic.appaudiclass.databinding.FragmentStocktakingBinding
import com.dvalic.appaudiclass.databinding.FragmentVersionsBinding

class StocktakingFragment : Fragment(R.layout.fragment_stocktaking) {

    private lateinit var binding: FragmentStocktakingBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStocktakingBinding.bind(view)
    }
}