package com.dvalic.appaudiclass.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ViewHolderMain<T>(itemView: View):RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}