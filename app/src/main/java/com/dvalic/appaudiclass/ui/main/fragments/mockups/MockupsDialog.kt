package com.dvalic.appaudiclass.ui.main.fragments.mockups

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.dvalic.appaudiclass.data.models.Maquetas
import com.dvalic.appaudiclass.databinding.DialogMockupsBinding
import com.dvalic.appaudiclass.presentation.ViewModelData
import com.dvalic.appaudiclass.repositorys.network.InterfazFragments
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MockupsDialog : BottomSheetDialogFragment(),
    RecyclerMockups.OnMenuclickListener {
    private lateinit var binding: DialogMockupsBinding
    private var interfazFragments: InterfazFragments? = null
    private val mainViewModel: ViewModelData by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogMockupsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.get360mockups().observe(viewLifecycleOwner, { mockups ->
            binding.rvMockups.adapter = mockups.Maquetas?.let { RecyclerMockups(it, this) }
        })
    }

    override fun onMenuClick(maquetas: Maquetas) {
        val bundle = Bundle().apply {
            putString("Ruta",maquetas.Ruta)
            putString("NombreApp",maquetas.NombreApp)
            putString("Anio",maquetas.Anio)
            putString("Version",maquetas.Version)
        }
        interfazFragments?.showWebView(bundle)
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