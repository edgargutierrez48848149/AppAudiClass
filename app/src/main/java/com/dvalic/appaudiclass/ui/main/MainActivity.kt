package com.dvalic.appaudiclass.ui.main

import android.Manifest
import android.animation.LayoutTransition
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.dvalic.appaudiclass.R
import com.dvalic.appaudiclass.core.DialogView
import com.dvalic.appaudiclass.core.PdfViewerActivity
import com.dvalic.appaudiclass.core.Resource
import com.dvalic.appaudiclass.core.WebViewActivity
import com.dvalic.appaudiclass.data.network.ConnectionLiveData
import com.dvalic.appaudiclass.data.network.NetworkDataSource
import com.dvalic.appaudiclass.databinding.ActivityMainBinding
import com.dvalic.appaudiclass.presentation.ViewModelData
import com.dvalic.appaudiclass.presentation.ViewModelFactoryMain
import com.dvalic.appaudiclass.presentation.ViewModelMain
import com.dvalic.appaudiclass.repositorys.InterfazFragments
import com.dvalic.appaudiclass.repositorys.RepositoryImplementMain
import com.dvalic.appaudiclass.repositorys.RetrofitClient
import com.google.android.material.snackbar.Snackbar
import com.vmadalin.easypermissions.EasyPermissions

class MainActivity : AppCompatActivity(), InterfazFragments, EasyPermissions.PermissionCallbacks {

    private lateinit var connectionLiveData: ConnectionLiveData
    private lateinit var binding: ActivityMainBinding
    private val viewModelData: ViewModelData by viewModels()

    private val viewModel by viewModels<ViewModelMain> {
        ViewModelFactoryMain(
            RepositoryImplementMain(
                NetworkDataSource(RetrofitClient.webservice)
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkNetworkConnection()
        getModelsPolitics()

        val lt = LayoutTransition()
        lt.disableTransitionType(LayoutTransition.CHANGE_APPEARING)
        binding.clMainActivity.layoutTransition = lt
    }

    private fun checkNetworkConnection() {
        connectionLiveData = ConnectionLiveData(application)
        connectionLiveData.observe(this, { isConnected ->
            if (isConnected) {
                binding.tvConectionStatus.visibility = View.GONE
            } else {
                binding.tvConectionStatus.visibility = View.VISIBLE
            }
        })
    }

    private fun getModelsPolitics() {
        viewModel.fetchModels().observe(this, { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressIndicator.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressIndicator.visibility = View.GONE
                    viewModelData.setModels(result.data.first)
                    viewModelData.setPolitics(result.data.second)
                    viewModelData.set360mockups(result.data.third)
                }
                is Resource.Failure -> {
                    binding.progressIndicator.visibility = View.GONE
                    val mySnackbar = Snackbar.make(
                        binding.fragmentContainerView,
                        "Comprueba tu conexion a internet", Snackbar.LENGTH_INDEFINITE
                    )
                        .setBackgroundTint(getColor(R.color.black))
                        .setTextColor(getColor(R.color.white))
                        .setAction("Reintentar") { getModelsPolitics() }
                        .setActionTextColor(getColor(R.color.red))
                    mySnackbar.show()
                }
            }
        })
    }

    override fun showMainMenufragment() {
        showBars(false)
        findNavController(R.id.fragment_container_view).navigate(R.id.action_to_mainMenuFragment)
    }

    override fun showModelsfragment() {
        showBars(true)
        findNavController(R.id.fragment_container_view).navigate(R.id.action_mainMenuFragment_to_modelsFragment)
    }

    override fun showVersionsFragment(bundle: Bundle?) {
        showBars(true)
        findNavController(R.id.fragment_container_view).navigate(R.id.action_modelsFragment_to_versionsFragment)
    }

    override fun showBars(visibility: Boolean) {
        if (visibility) {
            binding.barLayout.visibility = View.VISIBLE
            binding.bottomNavigation.visibility = View.VISIBLE
        } else {
            binding.barLayout.visibility = View.GONE
            binding.bottomNavigation.visibility = View.GONE
        }
    }

    override fun showWebPage(url: String?) {
        if (!url.equals("")) {
            intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } else {
            showSnackbar("Pagina no diponible", 3)
        }
    }

    override fun showSnackbar(url: String?, type: Int) {
        var color = 0
        when (type) {
            1 -> color = getColor(R.color.green)
            2 -> color = getColor(R.color.yellow)
            3 -> color = getColor(R.color.red)
        }
        val mySnackbar = Snackbar.make(
            binding.fragmentContainerView,
            "$url", Snackbar.LENGTH_SHORT
        )
            .setBackgroundTint(getColor(R.color.black))
            .setTextColor(color)

        mySnackbar.show()

    }

    override fun showWebView(bundle: Bundle?) {
        val intent = Intent(this, WebViewActivity::class.java)
        bundle?.let { intent.putExtras(it) }
        startActivity(intent)
    }

    override fun showPdf(bundle: Bundle?) {
        if (permissionExternalStorage()) {
            val intent = Intent(this, PdfViewerActivity::class.java)
            bundle?.let { intent.putExtras(it) }
            startActivity(intent)
        } else {
            EasyPermissions.requestPermissions(
                this,
                "La Aplicacion requiere permiso para mostrar los documentos",
                1,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }
    }

    override fun showDialog(
        title: String?,
        description: String?,
        textPositiveButton: String?,
        textNegativeButton: String?,
        actionPositive: Unit?,
        actionNegative:Unit?
    ) {
        val bundle = Bundle()
        bundle.putString("title", title)
        bundle.putString("description", description)
        bundle.putString("positivebutton", textPositiveButton)
        bundle.putString("negativebutton", textNegativeButton)
        val dialogView = DialogView(actionPositive, actionNegative)
        dialogView.arguments = bundle
        dialogView.show(supportFragmentManager, "MY_BOTTOM_SHEET")
    }


    private fun permissionExternalStorage(): Boolean {
        return EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }


    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        showDialog(
            "Aviso",
            "Para visualizar documentos permita que ${resources.getString(R.string.app_name)} pueda acceder a archivos",
            "Ajustes",
            null,
             goToSettingsApp(),null
        )
    }

    private fun goToSettingsApp() {
        Toast.makeText(this, "prueba", Toast.LENGTH_SHORT).show()
        // val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        // intent.data = Uri.parse("package:$packageName")
        // startActivity(intent)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }
}