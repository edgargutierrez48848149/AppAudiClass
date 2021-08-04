package com.dvalic.appaudiclass.repositorys.network

import com.dvalic.appaudiclass.data.models.*
import com.dvalic.appaudiclass.data.network.NetworkDataSource
import com.google.gson.JsonObject

class RepositoryImplementMain (private val networkDataSource: NetworkDataSource) : RepositoryMain {

    override suspend fun getModels(): ModelModels = networkDataSource.getModels()

    override suspend fun getPolitics(): ModelPolitics = networkDataSource.getPolitics()

    override suspend fun get360mockups(): Model360mockups = networkDataSource.get360mockups()

    override suspend fun getUser(Correo:String,Clave: String): ModelGeneralUser = networkDataSource.getUser(Correo,Clave)

    override suspend fun getUserSocial(Correo:String): ModelGeneralUser = networkDataSource.getUserSocial(Correo)

    override suspend fun postUpdateUser(datosPerfil: JsonObject): ModelGeneralUser = networkDataSource.postUpdateUser(datosPerfil)
}