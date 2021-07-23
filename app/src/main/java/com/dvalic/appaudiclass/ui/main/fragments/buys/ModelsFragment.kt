package com.dvalic.appaudiclass.ui.main.fragments.buys

import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.dvalic.appaudiclass.R
import com.dvalic.appaudiclass.data.models.ModelModels
import com.dvalic.appaudiclass.databinding.FragmentModelsBinding
import com.dvalic.appaudiclass.presentation.ViewModelData
import com.dvalic.appaudiclass.ui.main.fragments.buys.adapters.ViewPagerDetails
import com.dvalic.appaudiclass.ui.main.fragments.buys.adapters.ViewPagerModels
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ModelsFragment : Fragment(R.layout.fragment_models) {

    private lateinit var binding: FragmentModelsBinding
    private val mainViewModel: ViewModelData by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentModelsBinding.bind(view)

        //Todo Disable scrolling in viewpager
        binding.vpDetails.isUserInputEnabled = false

        //Todo Get data
        mainViewModel.getModel().observe(viewLifecycleOwner, { models ->
            binding.vpModelos.adapter = models.Modelos?.let { ViewPagerModels(it) }
            binding.vpModelos.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(pageSelected: Int) {
                    binding.tvModelName.text = models.Modelos?.get(pageSelected)?.NombreModelo
                    binding.vpDetails.adapter = models.Modelos?.get(pageSelected)?.Anios?.let { ViewPagerDetails(it) }
                    TabLayoutMediator(binding.tabYear, binding.vpDetails) { tab, tabselected ->
                        tab.text = models.Modelos?.get(pageSelected)?.Anios?.get(tabselected)?. Anio
                    }.attach()
                }
            })
        })
    }
}