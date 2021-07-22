package com.dvalic.appaudiclass.ui.main.fragments.buys.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dvalic.appaudiclass.core.ViewHolderMain
import com.dvalic.appaudiclass.data.models.Modelos
import com.dvalic.appaudiclass.databinding.ItemPictureBinding
import java.util.ArrayList

class ViewPagerModels(private val models: ArrayList<Modelos>) : RecyclerView.Adapter<ViewHolderMain<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain<*> {
        val itemBinding = ItemPictureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolderMain<*>, position: Int) {
        when(holder){
            is ViewHolder -> holder.bind(models[position])
        }
    }

    override fun getItemCount(): Int = models.size

    private inner class ViewHolder(
        val binding:ItemPictureBinding,
        val context:Context
    ):ViewHolderMain<Modelos>(binding.root){
        override fun bind(item: Modelos) {
            Glide.with(context).load(item.Anios?.get(0)?.GamaColores?.get(0)?.Ruta).into(binding.ivPicture)
        }
    }
}