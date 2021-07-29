package com.dvalic.appaudiclass.repositorys

import com.dvalic.appaudiclass.data.models.Model360mockups
import com.dvalic.appaudiclass.data.models.ModelModels
import com.dvalic.appaudiclass.data.models.ModelPolitics

interface RepositoryMain {

    suspend fun getModels():ModelModels

    suspend fun getPolitics():ModelPolitics

    suspend fun get360mockups():Model360mockups
}