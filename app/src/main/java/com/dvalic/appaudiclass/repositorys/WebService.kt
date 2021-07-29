package com.dvalic.appaudiclass.repositorys

import com.dvalic.appaudiclass.core.Constants
import com.dvalic.appaudiclass.data.models.Model360mockups
import com.dvalic.appaudiclass.data.models.ModelModels
import com.dvalic.appaudiclass.data.models.ModelPolitics
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("api/Autos/GetModelosXMarca")
    suspend fun getModels (@Query("aIdMarca") aIdMarca: String): ModelModels

    @GET("api/Agencia/GetObtenerPoliticasXMarca")
    suspend fun getPolitics (@Query("aIdMarca") aIdMarca: String): ModelPolitics

    @GET("api/Autos/GetMaquetasXMarca")
    suspend fun get360mockups (@Query("aIdMarca") aIdMarca: String): Model360mockups
}

object RetrofitClient{
    val webservice: WebService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}