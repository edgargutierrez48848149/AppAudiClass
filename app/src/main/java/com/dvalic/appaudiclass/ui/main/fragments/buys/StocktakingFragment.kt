package com.dvalic.appaudiclass.ui.main.fragments.buys

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dvalic.appaudiclass.R
import com.dvalic.appaudiclass.databinding.FragmentStocktakingBinding

class StocktakingFragment : Fragment(R.layout.fragment_stocktaking) {

    private lateinit var binding: FragmentStocktakingBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStocktakingBinding.bind(view)
    }
}