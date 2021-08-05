package com.dvalic.appaudiclass.ui.main.fragments.menu

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dvalic.appaudiclass.R
import com.dvalic.appaudiclass.core.LayoutPagerManager
import com.dvalic.appaudiclass.data.models.ModelMenu
import com.dvalic.appaudiclass.data.models.ModelPolitics
import com.dvalic.appaudiclass.databinding.FragmentMainMenuBinding
import com.dvalic.appaudiclass.presentation.ViewModelData
import com.dvalic.appaudiclass.repositorys.network.InterfazFragments
import com.dvalic.appaudiclass.ui.main.fragments.menu.adapters.RecyclerMainMenu
import com.dvalic.appaudiclass.ui.main.fragments.mockups.MockupsDialog

class MainMenuFragment : Fragment(R.layout.fragment_main_menu),
    RecyclerMainMenu.OnMovieclickListener {

    private lateinit var binding: FragmentMainMenuBinding
    private var interfazFragments: InterfazFragments? = null
    private var modelPolitics: ModelPolitics? = null
    private val mainViewModel: ViewModelData by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainMenuBinding.bind(view)

        interfazFragments?.showBars(false)

        mainViewModel.getPolitics().observe(viewLifecycleOwner, {
            modelPolitics = it
        })

        val menu: ArrayList<ModelMenu> = arrayListOf()
        menu.add(ModelMenu(R.drawable.ic_seat_menu1, "SEAT"))
        menu.add(ModelMenu(R.drawable.ic_seat_menu2, "Canal"))
        menu.add(ModelMenu(R.drawable.ic_seat_menu3, "360°"))
        menu.add(ModelMenu(R.drawable.ic_seat_menu4, "Cómpralo"))
        menu.add(ModelMenu(R.drawable.ic_seat_menu5, "Nosotros"))

        binding.rvMenu.layoutManager = LayoutPagerManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false,
            5
        )
        binding.rvMenu.adapter = RecyclerMainMenu(menu, this)
        binding.rvMenu.suppressLayout(true)

        binding.ivSecondaryMenu.setOnClickListener { findNavController().navigate(R.id.action_mainMenuFragment_to_secondaryMenuFragment) }
    }

    override fun onMenuClick(menu: ModelMenu) {
        when (menu.text.toString()) {
            "SEAT" -> interfazFragments?.showWebPage(modelPolitics?.LinkPaginaOficial ?: "")
            "Canal" -> interfazFragments?.showWebPage(modelPolitics?.LinkVideo ?: "")
            "360°" -> {
                val dialog = MockupsDialog()
                dialog.show(childFragmentManager, "MY_BOTTOM_SHEET")
            }
            "Cómpralo" -> findNavController().navigate(R.id.action_mainMenuFragment_to_modelsFragment)
            "Nosotros" -> findNavController().navigate(R.id.action_mainMenuFragment_to_usFragment)
        }
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