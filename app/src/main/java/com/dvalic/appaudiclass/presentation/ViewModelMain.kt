package com.dvalic.appaudiclass.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.dvalic.appaudiclass.core.Resource
import com.dvalic.appaudiclass.repositorys.network.RepositoryMain
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class ViewModelMain(private val repositoryMain: RepositoryMain) : ViewModel() {

    fun fetchModels() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(Triple(repositoryMain.getModels(),repositoryMain.getPolitics(),repositoryMain.get360mockups())))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun fetchUser(Correo:String,Clave: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repositoryMain.getUser(Correo,Clave)))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun fetchUserSocial(Correo:String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repositoryMain.getUserSocial(Correo)))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun fetchUpdateUser(datosPerfil: JsonObject) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repositoryMain.postUpdateUser(datosPerfil)))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}

class ViewModelFactoryMain(private val repositoryMain: RepositoryMain):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(RepositoryMain::class.java).newInstance(repositoryMain)
    }
}