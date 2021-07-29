package com.dvalic.appaudiclass.ui.main.fragments.buys.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dvalic.appaudiclass.core.ViewHolderMain
import com.dvalic.appaudiclass.data.models.Anios
import com.dvalic.appaudiclass.data.models.GamaColores
import com.dvalic.appaudiclass.data.models.Modelos
import com.dvalic.appaudiclass.databinding.ItemColorBinding
import com.dvalic.appaudiclass.databinding.ItemPictureBinding
import com.dvalic.appaudiclass.databinding.ViewpagerDetailsBinding
import java.util.ArrayList

class RecyclerColors(private val item: ArrayList<GamaColores>) : RecyclerView.Adapter<ViewHolderMain<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain<*> {
        val itemBinding = ItemColorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolderMain<*>, position: Int) {
        when(holder){
            is ViewHolder -> holder.bind(item[position])
        }
    }

    override fun getItemCount(): Int = item.size

    private inner class ViewHolder(
        val binding:ItemColorBinding,
        val context:Context
    ):ViewHolderMain<GamaColores>(binding.root){
        override fun bind(item: GamaColores) {
            Glide.with(context).load(item.RutaMini).into(binding.ivFoto)
        }
    }
}