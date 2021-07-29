package com.dvalic.appaudiclass.ui.main.fragments.mockups

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dvalic.appaudiclass.core.ViewHolderMain
import com.dvalic.appaudiclass.data.models.Maquetas
import com.dvalic.appaudiclass.databinding.ItemMockupsBinding

class RecyclerMockups(
private val item: ArrayList<Maquetas>,
private val itemClickListener: OnMovieclickListener,
) : RecyclerView.Adapter<ViewHolderMain<*>>() {

    interface OnMovieclickListener {
        fun onMenuClick(menu: Maquetas)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain<*> {
        val itemBinding = ItemMockupsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val itemholder = ViewHolder(itemBinding)
        itemBinding.root.setOnClickListener {
            val position = itemholder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            itemClickListener.onMenuClick(item[position])
        }
        return itemholder
    }

    override fun onBindViewHolder(holder: ViewHolderMain<*>, position: Int) {
        when(holder){
            is ViewHolder -> holder.bind(item[position])
        }
    }

    override fun getItemCount(): Int = item.size

    private inner class ViewHolder(
        val binding: ItemMockupsBinding,
    ):ViewHolderMain<Maquetas>(binding.root){
        override fun bind(item: Maquetas) {
            binding.tvMockups.text = item.NombreApp
        }
    }
}