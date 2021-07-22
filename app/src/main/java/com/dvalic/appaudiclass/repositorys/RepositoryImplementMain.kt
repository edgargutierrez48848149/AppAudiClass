package com.dvalic.appaudiclass.repositorys

import com.dvalic.appaudiclass.data.models.ModelModels
import com.dvalic.appaudiclass.data.network.NetworkDataSource

class RepositoryImplementMain (private val networkDataSource: NetworkDataSource) : RepositoryMain{

    override suspend fun getModels(): ModelModels = networkDataSource.getModels()
}