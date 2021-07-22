package com.dvalic.appaudiclass.repositorys

import com.dvalic.appaudiclass.data.models.ModelModels

interface RepositoryMain {

    suspend fun getModels():ModelModels
}