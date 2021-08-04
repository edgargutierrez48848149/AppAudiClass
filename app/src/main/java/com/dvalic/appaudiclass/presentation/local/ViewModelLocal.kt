package com.dvalic.appaudiclass.presentation.local

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.dvalic.appaudiclass.data.local.LocalUserDB
import com.dvalic.appaudiclass.data.local.LocalUserDao
import com.dvalic.appaudiclass.data.models.ModelUser
import com.dvalic.appaudiclass.repositorys.local.RepositoryLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelLocal(application: Application):AndroidViewModel(application) {

    val selectUser: LiveData<ModelUser>
    private val repositoryLocal: RepositoryLocal

    init {
        val localUserDao = LocalUserDB.createInstance(application).localUserDao()
        repositoryLocal = RepositoryLocal(localUserDao)
        selectUser = repositoryLocal.selectUser
    }

    fun inserUser(modelUser: ModelUser){
        viewModelScope.launch(Dispatchers.IO) {
            repositoryLocal.insertUser(modelUser)
        }
    }

    fun updateUser(modelUser: ModelUser){
        viewModelScope.launch(Dispatchers.IO) {
            repositoryLocal.updateUser(modelUser)
        }
    }

    fun deleteUser(){
        viewModelScope.launch(Dispatchers.IO) {
            repositoryLocal.deleteUser()
        }
    }
}