package com.dvalic.appaudiclass.ui.main.fragments.buys.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dvalic.appaudiclass.core.ViewHolderMain
import com.dvalic.appaudiclass.data.models.Modelos
import com.dvalic.appaudiclass.databinding.ItemPictureBinding
import java.util.*

class ViewPagerModels(
    private val item: ArrayList<Modelos>,
    private val itemClickListener: OnModelclickListener
) : RecyclerView.Adapter<ViewHolderMain<*>>() {

    interface OnModelclickListener {
        fun onModelClick(string: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain<*> {
        val itemBinding =
            ItemPictureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val itemholder = ViewHolder(itemBinding, parent.context)
        itemBinding.root.setOnClickListener {
            val position =
                itemholder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                    ?: return@setOnClickListener
            item[position].Anios?.get(0)?.GamaColores?.get(0)?.Ruta?.let { it1 ->
                itemClickListener.onModelClick(it1)
            }
        }
        return itemholder
    }

    override fun onBindViewHolder(holder: ViewHolderMain<*>, position: Int) {
        when (holder) {
            is ViewHolder -> holder.bind(item[position])
        }
    }

    override fun getItemCount(): Int = item.size

    private inner class ViewHolder(
        val binding: ItemPictureBinding,
        val context: Context
    ) : ViewHolderMain<Modelos>(binding.root) {
        override fun bind(item: Modelos) {
            Glide.with(context).load(item.Anios?.get(0)?.GamaColores?.get(0)?.Ruta)
                .into(binding.ivPicture)
        }
    }
}