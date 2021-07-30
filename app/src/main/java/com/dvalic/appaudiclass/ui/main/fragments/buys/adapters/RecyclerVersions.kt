package com.dvalic.appaudiclass.ui.main.fragments.buys.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dvalic.appaudiclass.core.ViewHolderMain
import com.dvalic.appaudiclass.data.models.Versiones
import com.dvalic.appaudiclass.databinding.ItemVersionBinding
import java.util.*

class RecyclerVersions(
    private val element: ArrayList<Versiones>,
    private val itemClickListener: OnVersionclickListener,
) : RecyclerView.Adapter<ViewHolderMain<*>>() {

    interface OnVersionclickListener {
        fun onVersionClick(version: Versiones)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain<*> {
        val itemBinding =
            ItemVersionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val itemholder = ViewHolder(itemBinding)
        itemBinding.root.setOnClickListener {
            val position =
                itemholder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                    ?: return@setOnClickListener
            itemClickListener.onVersionClick(element[position])
        }
        return itemholder
    }

    override fun onBindViewHolder(holder: ViewHolderMain<*>, position: Int) {
        when (holder) {
            is ViewHolder -> holder.bind(element[position])
        }
    }

    override fun getItemCount(): Int = element.size

    private inner class ViewHolder(
        val binding: ItemVersionBinding,
    ) : ViewHolderMain<Versiones>(binding.root) {
        override fun bind(item: Versiones) {
            if (this.bindingAdapterPosition == element.size - 1) {
                binding.linea.visibility = View.VISIBLE
            }
            binding.tvVersion.text = item.Nombre
        }
    }
}