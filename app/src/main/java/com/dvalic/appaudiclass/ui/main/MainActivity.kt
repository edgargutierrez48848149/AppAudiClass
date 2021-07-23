package com.dvalic.appaudiclass.ui.main

import android.os.Binder
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dvalic.appaudiclass.R
import com.dvalic.appaudiclass.core.Resource
import com.dvalic.appaudiclass.data.network.NetworkDataSource
import com.dvalic.appaudiclass.databinding.ActivityMainBinding
import com.dvalic.appaudiclass.presentation.ViewModelData
import com.dvalic.appaudiclass.presentation.ViewModelFactoryMain
import com.dvalic.appaudiclass.presentation.ViewModelMain
import com.dvalic.appaudiclass.repositorys.RepositoryImplementMain
import com.dvalic.appaudiclass.repositorys.RetrofitClient
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.progressindicator.LinearProgressIndicator

class MainActivity : AppCompatActivity() {

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

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.title == "Inicio") {

            }
            when (item.title == "Usuario") {

            }
            true
        }

        viewModel.fetchModels().observe(this, { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progress.visibility = View.GONE
                    viewModelData.setModel(result.data)
                }
                is Resource.Failure -> {
                    binding.progress.visibility = View.GONE
                }
            }
        })
    }
}