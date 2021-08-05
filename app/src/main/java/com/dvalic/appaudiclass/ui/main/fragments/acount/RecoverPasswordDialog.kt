package com.dvalic.appaudiclass.ui.main.fragments.acount

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.dvalic.appaudiclass.databinding.DialogRecoverPasswordBinding

class RecoverPasswordDialog : DialogFragment() {

    private lateinit var binding: DialogRecoverPasswordBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogRecoverPasswordBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(context)
        builder.setView(binding.root)
        return builder.create()
    }

}