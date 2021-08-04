package com.dvalic.appaudiclass.repositorys.local

import androidx.lifecycle.LiveData
import com.dvalic.appaudiclass.data.local.LocalUserDao
import com.dvalic.appaudiclass.data.models.ModelUser

class RepositoryLocal (private val localUserDao: LocalUserDao) {

    val selectUser: LiveData<ModelUser> = localUserDao.selectUser()

    suspend fun insertUser(modelUser: ModelUser){
        localUserDao.insertUser(modelUser)
    }

    suspend fun updateUser(modelUser: ModelUser){
        localUserDao.updateUser(modelUser)
    }

    suspend fun deleteUser(){
        localUserDao.deleteUser()
    }
}