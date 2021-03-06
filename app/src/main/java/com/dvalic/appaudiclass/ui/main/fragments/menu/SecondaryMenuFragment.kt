package com.dvalic.appaudiclass.ui.main.fragments.menu

import android.animation.LayoutTransition
import android.content.Context
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.dvalic.appaudiclass.R
import com.dvalic.appaudiclass.data.models.PoliticasAgencias
import com.dvalic.appaudiclass.databinding.FragmentSecondaryMenuBinding
import com.dvalic.appaudiclass.presentation.ViewModelData
import com.dvalic.appaudiclass.repositorys.network.InterfazFragments
import com.dvalic.appaudiclass.ui.main.fragments.menu.adapters.RecyclerSecondaryMenuPolitics

class SecondaryMenuFragment : Fragment(R.layout.fragment_secondary_menu),
    RecyclerSecondaryMenuPolitics.OnClickPolitics {

    private lateinit var binding: FragmentSecondaryMenuBinding
    private var interfazFragments: InterfazFragments? = null
    private val mainViewModel: ViewModelData by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSecondaryMenuBinding.bind(view)

        interfazFragments?.showBars(false)

        mainViewModel.getPolitics().observe(viewLifecycleOwner, { politics ->
            binding.rvPolitics.adapter =
                politics.PoliticasAgencias?.let { RecyclerSecondaryMenuPolitics(it, this) }
            binding.cvTermsConditions.setOnClickListener {
                val bundle = Bundle().apply {
                    putString("Ruta", politics.TerminoYCondiciones)
                    putInt("Type", 2)
                }
                interfazFragments?.showPdf(bundle)
            }
            binding.cvNoticePrivacy.setOnClickListener {
                val bundle = Bundle().apply {
                    putString("Ruta", politics.AvisoDePrivacidad)
                    putInt("Type", 2)
                }
                interfazFragments?.showPdf(bundle)
            }
        })

        binding.ivClose.setOnClickListener { requireActivity().onBackPressed() }

        binding.cvPurchasingProcess.setOnClickListener {
            interfazFragments?.showContact()
        }

        binding.cvPolitics.setOnClickListener {
            binding.ivPolitics.animate()
                .rotation(if (binding.rvPolitics.visibility == View.VISIBLE) 0f else 180f).apply {
                    duration = 400
                    interpolator = DecelerateInterpolator()
                }.start()
            binding.rvPolitics.visibility =
                if (binding.rvPolitics.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }

        binding.cvLegal.setOnClickListener {
            binding.ivLegal.animate()
                .rotation(if (binding.llLegal.visibility == View.VISIBLE) 0f else 180f).apply {
                    duration = 400
                    interpolator = DecelerateInterpolator()
                }.start()
            binding.llLegal.visibility =
                if (binding.llLegal.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }

        binding.cvUser.setOnClickListener {
            interfazFragments?.showAcount()
        }

        binding.cvContacto.setOnClickListener {
            interfazFragments?.showContact()
        }

        binding.cvShare.setOnClickListener {
            interfazFragments?.showContact()
        }

    }

    override fun onClickPolitics(politics: PoliticasAgencias) {
        val bundle = Bundle().apply {
            putString("Ruta", politics.Politicas)
            putInt("Type", 2)
        }
        interfazFragments?.showPdf(bundle)
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