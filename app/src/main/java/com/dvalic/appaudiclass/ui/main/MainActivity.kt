package com.dvalic.appaudiclass.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.findNavController
import com.dvalic.appaudiclass.R
import com.dvalic.appaudiclass.core.Resource
import com.dvalic.appaudiclass.data.network.NetworkConnection
import com.dvalic.appaudiclass.data.network.NetworkDataSource
import com.dvalic.appaudiclass.databinding.ActivityMainBinding
import com.dvalic.appaudiclass.presentation.ViewModelData
import com.dvalic.appaudiclass.presentation.ViewModelFactoryMain
import com.dvalic.appaudiclass.presentation.ViewModelMain
import com.dvalic.appaudiclass.repositorys.InterfazFragments
import com.dvalic.appaudiclass.repositorys.RepositoryImplementMain
import com.dvalic.appaudiclass.repositorys.RetrofitClient
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), InterfazFragments {

    lateinit var networkConnection: NetworkConnection

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

        networkConnection = NetworkConnection(this)
        val isNetworkAviable = networkConnection.observeAsState(false).value
        ConnectivityMonitor(isNetworkAviable)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.title == "Inicio") {
            }
            when (item.title == "Usuario") {
            }
            true
        }
        getModelsPolitics()
    }

    private fun ConnectivityMonitor(inNetworkAviable:Boolean){
        if (inNetworkAviable) {
            binding.tvConectionStatus.text = "En Linea"
            binding.tvConectionStatus.setBackgroundColor(getColor(R.color.green))
            binding.tvConectionStatus.visibility = View.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed({
                binding.tvConectionStatus.visibility = View.GONE
            }, 1500)
            if (viewModelData.getModels().value == null || viewModelData.getPolitics().value == null) {
                getModelsPolitics()
            }
        } else {
            binding.tvConectionStatus.text = "Sin conexión"
            binding.tvConectionStatus.setBackgroundColor(getColor(R.color.black))
            binding.tvConectionStatus.visibility = View.VISIBLE

        }
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
                }
                is Resource.Failure -> {
                    binding.progressIndicator.visibility = View.GONE
                    val mySnackbar = Snackbar.make(binding.fragmentContainerView,
                            "Comprueba tu conexion a internet", Snackbar.LENGTH_INDEFINITE)
                            .setBackgroundTint(getColor(R.color.black))
                            .setTextColor(getColor(R.color.white))
                            .setAction("Reintentar") { getModelsPolitics() }
                            .setActionTextColor(getColor(R.color.red))
                    mySnackbar.show()
                }
            }
        })
    }

    override fun showBars(visivility: Boolean) {
        if (visivility) {
            binding.barLayout.visibility = View.VISIBLE
            binding.bottomNavigation.visibility = View.VISIBLE
        } else {
            binding.barLayout.visibility = View.GONE
            binding.bottomNavigation.visibility = View.GONE
        }
    }

    override fun showMainMenufragment() {
        showBars(false)
        findNavController(R.id.fragment_container_view).navigate(R.id.action_to_mainMenuFragment)
    }

    override fun showModelsfragment() {
        showBars(true)
        findNavController(R.id.fragment_container_view).navigate(R.id.action_mainMenuFragment_to_modelsFragment)
    }

    override fun showWebPage(url: String?) {
        if (!url.equals("")) {
            intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } else {
            showSnackbar("Pagina no diponible", 3)
        }
    }

    override fun showSnackbar(text: String?, type: Int) {
        var color = 0
        when (type) {
            1 -> color = getColor(R.color.green)
            2 -> color = getColor(R.color.yellow)
            3 -> color = getColor(R.color.red)
        }
        val mySnackbar = Snackbar.make(binding.fragmentContainerView,
                "$text", Snackbar.LENGTH_SHORT)
                .setBackgroundTint(getColor(R.color.black))
                .setTextColor(color)

        mySnackbar.show()

    }
}