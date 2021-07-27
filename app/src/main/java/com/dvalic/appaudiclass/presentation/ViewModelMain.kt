package com.dvalic.appaudiclass.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.dvalic.appaudiclass.core.Resource
import com.dvalic.appaudiclass.repositorys.RepositoryMain
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class ViewModelMain(private val repositoryMain: RepositoryMain) : ViewModel() {

    fun fetchModels() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(Pair(repositoryMain.getModels(),repositoryMain.getPolitics())))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}

class ViewModelFactoryMain(private val repositoryMain: RepositoryMain):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(RepositoryMain::class.java).newInstance(repositoryMain)
    }
}