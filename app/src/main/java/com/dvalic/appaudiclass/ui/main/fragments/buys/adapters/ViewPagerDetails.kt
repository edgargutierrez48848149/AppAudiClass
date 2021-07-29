package com.dvalic.appaudiclass.ui.main.fragments.buys.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dvalic.appaudiclass.core.LayoutPagerManager
import com.dvalic.appaudiclass.core.ViewHolderMain
import com.dvalic.appaudiclass.data.models.Anios
import com.dvalic.appaudiclass.databinding.ViewpagerDetailsBinding
import java.util.*

class ViewPagerDetails(private val item: ArrayList<Anios>) : RecyclerView.Adapter<ViewHolderMain<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain<*> {
        val itemBinding = ViewpagerDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolderMain<*>, position: Int) {
        when(holder){
            is ViewHolder -> holder.bind(item[position])
        }
    }

    override fun getItemCount(): Int = item.size

    private inner class ViewHolder(
        val binding:ViewpagerDetailsBinding,
        val context:Context
    ):ViewHolderMain<Anios>(binding.root){
        override fun bind(item: Anios) {
            if (item.Versiones?.size!! <= 4){
                binding.rvVersions.layoutManager = LayoutPagerManager(context,LinearLayoutManager.HORIZONTAL,false,item.Versiones.size)
            }
            binding.rvVersions.adapter = RecyclerVersions(item.Versiones)
            binding.rvColors.layoutManager = LayoutPagerManager(context,LinearLayoutManager.HORIZONTAL,false,5)
            binding.rvColors.adapter = item.GamaColores?.let { RecyclerColors(it) }
            binding.tvNameColor.text = item.GamaColores?.get(0)?.Nombre
            binding.tvDetail1.text = item.Atributo1
            binding.tvDetail2.text = item.Atributo2
            binding.tvDetail3.text = item.Atributo3
            binding.tvDetail4.text = item.Atributo4
            binding.tvDetail5.text = item.Atributo5
            binding.tvDetail6.text = item.Atributo6
        }
    }
}