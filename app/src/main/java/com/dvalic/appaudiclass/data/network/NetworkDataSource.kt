package com.dvalic.appaudiclass.data.network

import com.dvalic.appaudiclass.core.Constants
import com.dvalic.appaudiclass.data.models.*
import com.dvalic.appaudiclass.repositorys.network.WebService
import com.google.gson.JsonObject

class NetworkDataSource(private val webService: WebService) {

    suspend fun getModels(): ModelModels = webService.getModels(Constants.ID_MARK)

    suspend fun getPolitics(): ModelPolitics = webService.getPolitics(Constants.ID_MARK)

    suspend fun get360mockups(): Model360mockups = webService.get360mockups(Constants.ID_MARK)

    suspend fun getUser(Correo:String,Clave: String): ModelGeneralUser = webService.getUser(Correo,Clave,Constants.ID_APP)

    suspend fun getUserSocial(Correo:String): ModelGeneralUser = webService.getUserSocial(Correo,Constants.ID_APP)

    suspend fun postUpdateUser(datosPerfil: JsonObject): ModelGeneralUser = webService.postUpdateUser(datosPerfil)
}