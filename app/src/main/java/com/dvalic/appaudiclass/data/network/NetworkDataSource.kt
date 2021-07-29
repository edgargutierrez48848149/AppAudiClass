package com.dvalic.appaudiclass.data.network

import com.dvalic.appaudiclass.core.Constants
import com.dvalic.appaudiclass.data.models.Model360mockups
import com.dvalic.appaudiclass.data.models.ModelModels
import com.dvalic.appaudiclass.data.models.ModelPolitics
import com.dvalic.appaudiclass.data.models.PoliticasAgencias
import com.dvalic.appaudiclass.repositorys.WebService

class NetworkDataSource(private val webService: WebService) {

    suspend fun getModels(): ModelModels = webService.getModels(Constants.ID_MARK)

    suspend fun getPolitics(): ModelPolitics = webService.getPolitics(Constants.ID_MARK)

    suspend fun get360mockups(): Model360mockups = webService.get360mockups(Constants.ID_MARK)
}