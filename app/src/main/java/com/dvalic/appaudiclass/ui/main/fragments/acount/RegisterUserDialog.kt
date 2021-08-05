package com.dvalic.appaudiclass.ui.main.fragments.acount

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.dvalic.appaudiclass.databinding.DialogRegisterUserBinding

class RegisterUserDialog : DialogFragment() {

    private lateinit var binding: DialogRegisterUserBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogRegisterUserBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(context)
        builder.setView(binding.root)
        return builder.create()
    }
}