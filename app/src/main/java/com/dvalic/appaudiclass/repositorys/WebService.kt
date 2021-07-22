package com.dvalic.appaudiclass.repositorys

import com.dvalic.appaudiclass.core.Constants
import com.dvalic.appaudiclass.data.models.ModelModels
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("api/Autos/GetModelosXMarca")
    suspend fun getModels (@Query("aIdMarca") aIdMarca: String): ModelModels
}

object RetrofitClient{
    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}