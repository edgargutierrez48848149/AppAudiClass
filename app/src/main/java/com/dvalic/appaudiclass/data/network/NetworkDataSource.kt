package com.dvalic.appaudiclass.data.network

import com.dvalic.appaudiclass.core.Constants
import com.dvalic.appaudiclass.data.models.ModelModels
import com.dvalic.appaudiclass.repositorys.WebService

class NetworkDataSource(private val webService: WebService) {

    suspend fun getModels(): ModelModels = webService.getModels(Constants.ID_MARK)
}