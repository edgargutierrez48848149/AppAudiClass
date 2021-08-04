package com.dvalic.appaudiclass.repositorys.network

import com.dvalic.appaudiclass.data.models.*
import com.google.gson.JsonObject

interface RepositoryMain {

    suspend fun getModels():ModelModels

    suspend fun getPolitics():ModelPolitics

    suspend fun get360mockups():Model360mockups

    suspend fun getUser(Correo:String,Clave: String): ModelGeneralUser

    suspend fun getUserSocial(Correo:String): ModelGeneralUser

    suspend fun postUpdateUser(datosPerfil: JsonObject): ModelGeneralUser
}