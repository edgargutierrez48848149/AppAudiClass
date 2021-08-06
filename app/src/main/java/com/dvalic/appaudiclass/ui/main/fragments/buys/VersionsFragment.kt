package com.dvalic.appaudiclass.ui.main.fragments.buys

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dvalic.appaudiclass.R
import com.dvalic.appaudiclass.core.LayoutPagerManager
import com.dvalic.appaudiclass.data.models.Versiones
import com.dvalic.appaudiclass.databinding.FragmentVersionsBinding
import com.dvalic.appaudiclass.repositorys.network.InterfazFragments
import com.dvalic.appaudiclass.ui.main.fragments.buys.adapters.RecyclerColorsModel
import com.dvalic.appaudiclass.ui.main.fragments.buys.adapters.RecyclerColorsVersion

class VersionsFragment : Fragment(R.layout.fragment_versions) {

    private lateinit var binding: FragmentVersionsBinding
    private var interfazFragments: InterfazFragments? = null
    private var modelName: String? = ""
    private var version: Versiones = Versiones()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            modelName = bundle.getString("modelName")
            version = bundle.get("version") as Versiones
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentVersionsBinding.bind(view)
        binding.tvModelName.text = modelName

        Glide.with(requireContext()).load(version.Colores?.get(0)?.Ruta).into(binding.ivVersion)

        binding.tvTransmission1.text = version.PrecioTransmision?.get(0)?.Transmision
        binding.tvListPrice1.text = version.PrecioTransmision?.get(0)?.PrecioLista
        binding.tvPurchasePrice1.text = version.PrecioTransmision?.get(0)?.PrecioContado
        if (version.PrecioTransmision?.size!! > 1) {
            binding.cvPrice2.visibility = View.VISIBLE
            binding.tvTransmission2.text = version.PrecioTransmision?.get(1)?.Transmision
            binding.tvListPrice2.text = version.PrecioTransmision?.get(1)?.PrecioLista
            binding.tvPurchasePrice2.text = version.PrecioTransmision?.get(1)?.PrecioContado
        }

        binding.rvColors.layoutManager =
            LayoutPagerManager(requireContext(), LinearLayoutManager.HORIZONTAL, false, 5)
        binding.rvColors.adapter = version.Colores?.let { RecyclerColorsVersion(it) }
        binding.tvNameColor.text = version.Colores?.get(0)?.Nombre

        binding.ivVersion.setOnClickListener { interfazFragments?.showImage(version.Colores?.get(0)?.Ruta) }

        binding.cvMockup.setOnClickListener {
            val bundle = Bundle().apply {
                putString("Ruta", version.Ruta360.toString())
            }
            interfazFragments?.showWebView(bundle)
        }

        binding.cvDataSheet.setOnClickListener {
            val bundle = Bundle().apply {
                putString("Ruta", version.RutaFichaTecnica.toString())
                putInt("Type", 2)
            }
            interfazFragments?.showPdf(bundle)
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