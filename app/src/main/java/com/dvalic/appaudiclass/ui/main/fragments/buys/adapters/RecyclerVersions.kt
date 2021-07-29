package com.dvalic.appaudiclass.ui.main.fragments.buys.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dvalic.appaudiclass.core.ViewHolderMain
import com.dvalic.appaudiclass.data.models.Anios
import com.dvalic.appaudiclass.data.models.GamaColores
import com.dvalic.appaudiclass.data.models.Modelos
import com.dvalic.appaudiclass.data.models.Versiones
import com.dvalic.appaudiclass.databinding.ItemColorBinding
import com.dvalic.appaudiclass.databinding.ItemPictureBinding
import com.dvalic.appaudiclass.databinding.ItemVersionBinding
import com.dvalic.appaudiclass.databinding.ViewpagerDetailsBinding
import java.util.ArrayList

class RecyclerVersions(private val element: ArrayList<Versiones>) : RecyclerView.Adapter<ViewHolderMain<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain<*> {
        val itemBinding = ItemVersionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolderMain<*>, position: Int) {
        when(holder){
            is ViewHolder -> holder.bind(element[position])
        }
    }

    override fun getItemCount(): Int = element.size

    private inner class ViewHolder(
        val binding:ItemVersionBinding,
        val context:Context
    ):ViewHolderMain<Versiones>(binding.root){
        override fun bind(item: Versiones) {
            if (this.adapterPosition == element.size-1){
                binding.linea.visibility = View.VISIBLE
            }
            binding.tvVersion.text = item.Nombre
        }
    }
}