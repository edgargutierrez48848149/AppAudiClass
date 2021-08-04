package com.dvalic.appaudiclass.ui.main.fragments.acount

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.dvalic.appaudiclass.R
import com.dvalic.appaudiclass.core.Resource
import com.dvalic.appaudiclass.data.local.LocalUserDB
import com.dvalic.appaudiclass.data.local.LocalUserDao
import com.dvalic.appaudiclass.data.network.NetworkDataSource
import com.dvalic.appaudiclass.databinding.DialogLoginBinding
import com.dvalic.appaudiclass.databinding.DialogProfileBinding
import com.dvalic.appaudiclass.presentation.ViewModelFactoryMain
import com.dvalic.appaudiclass.presentation.ViewModelMain
import com.dvalic.appaudiclass.presentation.local.ViewModelLocal
import com.dvalic.appaudiclass.repositorys.network.InterfazFragments
import com.dvalic.appaudiclass.repositorys.network.RepositoryImplementMain
import com.dvalic.appaudiclass.repositorys.network.RetrofitClient
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class LoginDialog : DialogFragment() {

    private lateinit var binding: DialogLoginBinding
    private var interfazFragments: InterfazFragments? = null
    private lateinit var viewModelLocal: ViewModelLocal
    private val viewModel by viewModels<ViewModelMain> {
        ViewModelFactoryMain(
            RepositoryImplementMain(
                NetworkDataSource(RetrofitClient.webservice)
            )
        )
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogLoginBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(context)
        builder.setView(binding.root)

        viewModelLocal = ViewModelProvider(this)[ViewModelLocal::class.java]

        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding.tilEmail.error = null
            }

            override fun afterTextChanged(s: Editable) {}
        })

        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding.tilPassword.error = null
            }

            override fun afterTextChanged(s: Editable) {}
        })

        binding.btnSignIn.setOnClickListener {
            if (validateFields()) {
                startServiceUser()
            }
        }

        binding.btnToRegister.setOnClickListener {

        }
        return builder.create()
    }

    private fun validateFields(): Boolean {
        var camposCompletos = true
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        val correo: String = binding.etEmail.text.toString()
        if (!correo.matches(emailPattern.toRegex())) {
            binding.tilEmail.error = "error"
            camposCompletos = false
        }
        if (binding.etPassword.text.toString() == "") {
            binding.tilPassword.error = "getString()"
            camposCompletos = false
        }
        return camposCompletos
    }

    private fun startServiceUser() {
        viewModel.fetchUserSocial(binding.etEmail.text.toString()).observe(
            this,
            { result ->
                when (result) {
                    is Resource.Loading -> {
                        binding.progressIndicator.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressIndicator.visibility = View.GONE
                        if (result.data.Ok.equals("SI")){
                            result.data.Objeto?.let { viewModelLocal.inserUser(it) }
                            interfazFragments?.logIn()
                            dismiss()
                        }
                        Toast.makeText(context, result.data.Mensaje, Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Failure -> {
                        binding.progressIndicator.visibility = View.GONE
                    }
                }
            })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is InterfazFragments) {
            interfazFragments = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        interfazFragments = null
    }

}