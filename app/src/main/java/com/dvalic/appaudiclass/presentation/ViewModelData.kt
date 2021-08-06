package com.dvalic.appaudiclass.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dvalic.appaudiclass.data.models.*

class ViewModelData : ViewModel() {
    private val models = MutableLiveData<ModelModels>()
    private val politics = MutableLiveData<ModelPolitics>()
    private val mockups = MutableLiveData<Model360mockups>()
    private val user = MutableLiveData<ModelUser>()

    fun setModels(dataModels: ModelModels) {
        models.value = dataModels
    }
    fun getModels(): LiveData<ModelModels> {
        return models
    }

    fun setPolitics(dataPolitics: ModelPolitics) {
        politics.value = dataPolitics
    }
    fun getPolitics():LiveData<ModelPolitics>{
        return  politics
    }

    fun set360mockups(data360mockups: Model360mockups) {
        mockups.value = data360mockups
    }
    fun get360mockups():LiveData<Model360mockups>{
        return  mockups
    }

    fun setUser(dataUser: ModelUser) {
        user.value = dataUser
    }
    fun getUser():LiveData<ModelUser>{
        return  user
    }
}