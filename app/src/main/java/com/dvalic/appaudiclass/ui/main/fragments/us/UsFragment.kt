package com.dvalic.appaudiclass.ui.main.fragments.us

import android.animation.LayoutTransition
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import com.dvalic.appaudiclass.R
import com.dvalic.appaudiclass.databinding.FragmentUsBinding
import com.dvalic.appaudiclass.repositorys.InterfazFragments
import com.dvalic.appaudiclass.ui.main.fragments.us.adapters.ViewPagerUs
import com.google.android.material.tabs.TabLayoutMediator

class UsFragment : Fragment(R.layout.fragment_us) {

    private lateinit var binding:FragmentUsBinding
    private var interfazFragments: InterfazFragments? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUsBinding.bind(view)

        val lt = LayoutTransition()
        lt.disableTransitionType(LayoutTransition.CHANGING)
        binding.clFragmentUs.layoutTransition = lt

        interfazFragments?.showBars(true)

        Handler(Looper.getMainLooper()).postDelayed({
            binding.tabNosotros.visibility = View.VISIBLE
        }, 200)
        Handler(Looper.getMainLooper()).postDelayed({
            binding.vpUs.visibility = View.VISIBLE
        }, 300)

        val fragments: ArrayList<String> = arrayListOf()
        fragments.add("Grupo Autofin Mexico")
        fragments.add("Furia Motors")
        fragments.add("Nuestra App")
        binding.tabNosotros

        val adapter = ViewPagerUs(this)
        binding.vpUs.adapter = adapter

        TabLayoutMediator(binding.tabNosotros, binding.vpUs) { tab, tabselected ->
            tab.text = fragments[tabselected]
        }.attach()
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