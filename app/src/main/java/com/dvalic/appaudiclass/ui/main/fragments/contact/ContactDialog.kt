package com.dvalic.appaudiclass.ui.main.fragments.contact

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.dvalic.appaudiclass.databinding.DialogContactBinding

class ContactDialog : DialogFragment() {

    private lateinit var binding:DialogContactBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogContactBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(context)
        builder.setView(binding.root)
        return builder.create()
    }
}