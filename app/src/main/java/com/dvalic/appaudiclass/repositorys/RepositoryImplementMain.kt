package com.dvalic.appaudiclass.repositorys

import com.dvalic.appaudiclass.data.models.Model360mockups
import com.dvalic.appaudiclass.data.models.ModelModels
import com.dvalic.appaudiclass.data.models.ModelPolitics
import com.dvalic.appaudiclass.data.network.NetworkDataSource

class RepositoryImplementMain (private val networkDataSource: NetworkDataSource) : RepositoryMain{

    override suspend fun getModels(): ModelModels = networkDataSource.getModels()

    override suspend fun getPolitics(): ModelPolitics = networkDataSource.getPolitics()

    override suspend fun get360mockups(): Model360mockups = networkDataSource.get360mockups()
}