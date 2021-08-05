package com.dvalic.appaudiclass.ui.main.fragments.acount

import android.animation.LayoutTransition
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.dvalic.appaudiclass.core.Constants
import com.dvalic.appaudiclass.core.Resource
import com.dvalic.appaudiclass.data.models.ModelUser
import com.dvalic.appaudiclass.data.network.NetworkDataSource
import com.dvalic.appaudiclass.databinding.DialogProfileBinding
import com.dvalic.appaudiclass.presentation.ViewModelData
import com.dvalic.appaudiclass.presentation.ViewModelFactoryMain
import com.dvalic.appaudiclass.presentation.ViewModelMain
import com.dvalic.appaudiclass.presentation.local.ViewModelLocal
import com.dvalic.appaudiclass.repositorys.network.InterfazDialogAction
import com.dvalic.appaudiclass.repositorys.network.InterfazFragments
import com.dvalic.appaudiclass.repositorys.network.RepositoryImplementMain
import com.dvalic.appaudiclass.repositorys.network.RetrofitClient
import com.google.gson.JsonObject

class ProfileDialog : DialogFragment() {

    private lateinit var binding: DialogProfileBinding
    private lateinit var viewModelLocal: ViewModelLocal
    private var interfazFragments: InterfazFragments? = null
    private val mainViewModel: ViewModelData by activityViewModels()
    private var user = ModelUser()
    private var edit: Boolean = false
    private val viewModel by viewModels<ViewModelMain> {
        ViewModelFactoryMain(
            RepositoryImplementMain(
                NetworkDataSource(RetrofitClient.webservice)
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogProfileBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(context)
        builder.setView(binding.root)

        viewModelLocal = ViewModelProvider(this)[ViewModelLocal::class.java]

        disableFields(true)

        mainViewModel.getUser().observe(this, {
            binding.etName.setText(it.Nombre)
            binding.etFistName.setText(it.ApellidoPaterno)
            binding.etLastName.setText(it.ApellidoMaterno)
            binding.etPhone.setText("${it.LadaMovil}${it.TelefonoMovil}")
            binding.etEmail.setText(it.Correo)
            user = it
        })

        binding.btnEdit.setOnClickListener { disableFields(false) }

        binding.btnCancel.setOnClickListener { disableFields(true) }

        binding.btnSignOut.setOnClickListener {
            if (edit) {
                interfazFragments?.showDialog(
                    "cerrar sesión",
                    "¿Confirma que desea cerrar sesión?",
                    "Cerrar sesión",
                    "cancelar",
                    object : InterfazDialogAction {
                        override fun okSelected(yesnot: Int?) {
                            interfazFragments?.logOut()
                            dismiss()
                        }
                    },
                    null
                )

            } else {
                startServiceUpdateUser()
            }
        }

        return builder.create()
    }

    @SuppressLint("SetTextI18n")
    private fun disableFields(disable: Boolean) {
        if (disable) {
            binding.tilName.isEnabled = false
            binding.tilFistName.isEnabled = false
            binding.tilLastName.isEnabled = false
            binding.tilPhone.isEnabled = false
            binding.imgProfile.isEnabled = false
            binding.btnSignOut.text = "Cerrar sesion"
            binding.btnEdit.visibility = View.VISIBLE
            binding.btnCancel.visibility = View.GONE
            binding.etName.setText(user.Nombre)
            binding.etFistName.setText(user.ApellidoPaterno)
            binding.etLastName.setText(user.ApellidoMaterno)
            binding.etPhone.setText("${user.LadaMovil}${user.TelefonoMovil}")
            edit = true
        } else {
            binding.tilName.isEnabled = true
            binding.tilFistName.isEnabled = true
            binding.tilLastName.isEnabled = true
            binding.tilPhone.isEnabled = true
            binding.imgProfile.isEnabled = true
            binding.btnSignOut.text = "Confirmar cambios"
            binding.btnEdit.visibility = View.GONE
            binding.btnCancel.visibility = View.VISIBLE
            edit = false
        }
    }

    private fun startServiceUpdateUser() {
        val datosPerfil = JsonObject()
        datosPerfil.addProperty("IdApps", Constants.ID_APP)
        datosPerfil.addProperty("IdCuenta", user.IdCuenta)
        datosPerfil.addProperty("IdPersona", "")
        datosPerfil.addProperty("Nombre", binding.etName.text.toString())
        datosPerfil.addProperty("ApellidoPaterno", binding.etFistName.text.toString())
        datosPerfil.addProperty("ApellidoMaterno", binding.etLastName.text.toString())
        datosPerfil.addProperty("Correo", user.Correo)
        datosPerfil.addProperty("Token", user.Token)
        datosPerfil.addProperty("LadaMovil", "52")
        datosPerfil.addProperty("TelefonoMovil", binding.etPhone.text.toString())
        viewModel.fetchUpdateUser(datosPerfil).observe(
            this,
            { result ->
                when (result) {
                    is Resource.Loading -> {
                        binding.progressIndicator.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressIndicator.visibility = View.GONE
                        Toast.makeText(
                            context,
                            result.data.Mensaje.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                        if (result.data.Ok.equals("SI")) {
                            result.data.Objeto?.let { viewModelLocal.updateUser(it) }
                        }
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