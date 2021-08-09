package com.dvalic.appaudiclass.ui.main.fragments.buys.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dvalic.appaudiclass.core.LayoutPagerManager
import com.dvalic.appaudiclass.core.ViewHolderMain
import com.dvalic.appaudiclass.data.models.Anios
import com.dvalic.appaudiclass.data.models.Versiones
import com.dvalic.appaudiclass.databinding.ViewpagerDetailsBinding
import com.dvalic.appaudiclass.presentation.ViewModelData
import com.dvalic.appaudiclass.repositorys.network.InterfazFragments
import java.util.*

class ViewPagerDetails(
    private val item: ArrayList<Anios>,
    private val interfazFragments: InterfazFragments,
    private val modelName: String
) : RecyclerView.Adapter<ViewHolderMain<*>>(), RecyclerVersions.OnVersionclickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain<*> {
        val itemBinding =
            ViewpagerDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolderMain<*>, position: Int) {
        when (holder) {
            is ViewHolder -> holder.bind(item[position])
        }
    }

    override fun getItemCount(): Int = item.size

    private inner class ViewHolder(
        val binding: ViewpagerDetailsBinding,
        val context: Context
    ) : ViewHolderMain<Anios>(binding.root) {
        override fun bind(item: Anios) {
            if (item.Versiones?.size!! <= 4) {
                binding.rvVersions.layoutManager = LayoutPagerManager(
                    context,
                    LinearLayoutManager.HORIZONTAL,
                    false,
                    item.Versiones.size
                )
            }
            binding.rvVersions.adapter = RecyclerVersions(item.Versiones, this@ViewPagerDetails)
            binding.rvColors.layoutManager = LayoutPagerManager(context, LinearLayoutManager.HORIZONTAL, false, 5)
            binding.rvColors.adapter = item.GamaColores?.let { RecyclerColorsModel(it) }
            binding.tvNameColor.text = item.GamaColores?.get(0)?.Nombre
            binding.tvDetail1.text = item.Atributo1?.replace("\\n", Objects.requireNonNull(System.getProperty("line.separator")))
            binding.tvDetail2.text = item.Atributo2?.replace("\\n", Objects.requireNonNull(System.getProperty("line.separator")))
            binding.tvDetail3.text = item.Atributo3?.replace("\\n", Objects.requireNonNull(System.getProperty("line.separator")))
            binding.tvDetail4.text = item.Atributo4?.replace("\\n", Objects.requireNonNull(System.getProperty("line.separator")))
            binding.tvDetail5.text = item.Atributo5?.replace("\\n", Objects.requireNonNull(System.getProperty("line.separator")))
            binding.tvDetail6.text = item.Atributo6?.replace("\\n", Objects.requireNonNull(System.getProperty("line.separator")))
        }
    }

    override fun onVersionClick(version: Versiones) {
        val bundle = Bundle()
        bundle.putString("modelName", modelName)
        bundle.putSerializable("version", version)
        interfazFragments.showVersionsFragment(bundle)
    }
}