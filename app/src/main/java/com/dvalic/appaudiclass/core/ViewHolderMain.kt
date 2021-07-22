package com.dvalic.appaudiclass.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ViewHolderMain<T>(view: View):RecyclerView.ViewHolder(view) {
    abstract fun bind(item: T)
}