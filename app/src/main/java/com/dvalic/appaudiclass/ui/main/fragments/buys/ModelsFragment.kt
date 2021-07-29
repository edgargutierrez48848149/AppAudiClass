package com.dvalic.appaudiclass.ui.main.fragments.buys

import android.animation.LayoutTransition
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.dvalic.appaudiclass.R
import com.dvalic.appaudiclass.databinding.FragmentModelsBinding
import com.dvalic.appaudiclass.presentation.ViewModelData
import com.dvalic.appaudiclass.repositorys.InterfazFragments
import com.dvalic.appaudiclass.ui.main.fragments.buys.adapters.ViewPagerDetails
import com.dvalic.appaudiclass.ui.main.fragments.buys.adapters.ViewPagerModels
import com.google.android.material.tabs.TabLayoutMediator

class ModelsFragment : Fragment(R.layout.fragment_models){

    private lateinit var binding: FragmentModelsBinding
    private var interfazFragments: InterfazFragments? = null
    private val mainViewModel: ViewModelData by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentModelsBinding.bind(view)

        val lt = LayoutTransition()
        lt.disableTransitionType(LayoutTransition.CHANGING)
        binding.clFragmentModels.layoutTransition = lt

        interfazFragments?.showBars(true)

        Handler(Looper.getMainLooper()).postDelayed({
            binding.vpModelos.visibility = View.VISIBLE
        }, 200)
        Handler(Looper.getMainLooper()).postDelayed({
            binding.tabYear.visibility = View.VISIBLE
        }, 300)
        Handler(Looper.getMainLooper()).postDelayed({
            binding.vpDetails.visibility = View.VISIBLE
        }, 400)

        binding.vpDetails.isUserInputEnabled = false
        mainViewModel.getModels().observe(viewLifecycleOwner, { models ->
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is InterfazFragments) {
            interfazFragments = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        interfazFragments = null
    }
}