package com.dvalic.appaudiclass.ui.main

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.dvalic.appaudiclass.R
import com.dvalic.appaudiclass.core.*
import com.dvalic.appaudiclass.data.models.ModelUser
import com.dvalic.appaudiclass.data.network.ConnectionLiveData
import com.dvalic.appaudiclass.data.network.NetworkDataSource
import com.dvalic.appaudiclass.databinding.ActivityMainBinding
import com.dvalic.appaudiclass.presentation.ViewModelData
import com.dvalic.appaudiclass.presentation.ViewModelFactoryMain
import com.dvalic.appaudiclass.presentation.ViewModelMain
import com.dvalic.appaudiclass.presentation.local.ViewModelLocal
import com.dvalic.appaudiclass.repositorys.network.InterfazDialogAction
import com.dvalic.appaudiclass.repositorys.network.InterfazFragments
import com.dvalic.appaudiclass.repositorys.network.RepositoryImplementMain
import com.dvalic.appaudiclass.repositorys.network.RetrofitClient
import com.dvalic.appaudiclass.ui.main.fragments.acount.LoginDialog
import com.dvalic.appaudiclass.ui.main.fragments.acount.ProfileDialog
import com.dvalic.appaudiclass.ui.main.fragments.contact.ContactDialog
import com.google.android.material.snackbar.Snackbar
import com.vmadalin.easypermissions.EasyPermissions
import java.io.File

class MainActivity : AppCompatActivity(), InterfazFragments, EasyPermissions.PermissionCallbacks {

    private lateinit var connectionLiveData: ConnectionLiveData
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModelLocal: ViewModelLocal
    private var user: Boolean = false
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

        viewModelLocal = ViewModelProvider(this)[ViewModelLocal::class.java]

        checkNetworkConnection()
        checkUserExistence()
        getModelsPolitics()

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_1 -> {
                    showMainMenufragment()
                    return@setOnItemSelectedListener true
                }
                R.id.item_2 -> {
                    showContact()
                    return@setOnItemSelectedListener true
                }
                R.id.item_3 -> {
                    return@setOnItemSelectedListener true
                }
                R.id.item_4 -> {
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }

    //Verifica el estado de la conexion de la aplicacion

    private fun checkNetworkConnection() {
        connectionLiveData = ConnectionLiveData(application)
        connectionLiveData.observe(this, { isConnected ->
            if (isConnected) {
                binding.tvConectionStatus.visibility = View.GONE
                if (viewModelData.getModels().value == null && viewModelData.getPolitics().value == null && viewModelData.get360mockups().value == null) {
                    getModelsPolitics()
                }
            } else {
                binding.tvConectionStatus.visibility = View.VISIBLE
            }
        })
    }

    //Obtiene los datos del servidor

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

    override fun showAcount() {
        if (user) {
            val dialog = ProfileDialog()
            dialog.show(supportFragmentManager, "dialog")
        } else {
            val dialog = LoginDialog()
            dialog.show(supportFragmentManager, "dialog")
        }
    }

    override fun showContact() {
        val dialog = ContactDialog()
        dialog.show(supportFragmentManager, "dialog")
    }

    //Verifica si existe usuario

    override fun checkUserExistence() {
        viewModelLocal.selectUser.observe(this, {
            if (it != null) {
                user = true
                viewModelData.setUser(it)
            } else {
                user = false
                val user = ModelUser()
                viewModelData.setUser(user)
            }
        })
    }

    override fun logOut() {
        viewModelLocal.deleteUser()
        checkUserExistence()
    }

    override fun logIn() {
        checkUserExistence()
        Handler(Looper.getMainLooper()).postDelayed(
            { showAcount() },
            500
        )

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

    override fun showImage(url: String?, drawable: Drawable?, bigPicture: Boolean?) {
        val dialog = ImageDialog(url,drawable,bigPicture)
        dialog.show(supportFragmentManager, "dialog")
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
        val mySnackbar = Snackbar.make(binding.fragmentContainerView, "$url", Snackbar.LENGTH_SHORT)
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
        actionPositive: InterfazDialogAction?,
        actionNegative: InterfazDialogAction?,
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
            "Ahora no",
            object : InterfazDialogAction {
                override fun okSelected(yesnot: Int?) {
                    goToSettingsApp()
                }
            },
            null
        )
    }

    private fun goToSettingsApp() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.parse("package:$packageName")
        startActivity(intent)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    //limpia el cache al finalizar la actividad

    override fun onDestroy() {
        super.onDestroy()
        try {
            trimCache(this)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    private fun trimCache(context: Context) {
        try {
            val dir = context.cacheDir
            if (dir != null && dir.isDirectory) {
                deleteDir(dir)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun deleteDir(dir: File?): Boolean {
        if (dir != null && dir.isDirectory) {
            val children = dir.list()
            for (i in children.indices) {
                val success = deleteDir(File(dir, children[i]))
                if (!success) {
                    return false
                }
            }
        }
        return dir!!.delete()
    }
}