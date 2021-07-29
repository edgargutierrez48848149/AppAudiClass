package com.dvalic.appaudiclass.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dvalic.appaudiclass.data.models.Model360mockups
import com.dvalic.appaudiclass.data.models.ModelModels
import com.dvalic.appaudiclass.data.models.ModelPolitics

class ViewModelData : ViewModel() {
    private val models = MutableLiveData<ModelModels>()
    private val politics = MutableLiveData<ModelPolitics>()
    private val mockups = MutableLiveData<Model360mockups>()

    fun setModels(dataModels: ModelModels) {
        models.value = dataModels
    }

    fun setPolitics(dataPolitics: ModelPolitics) {
        politics.value = dataPolitics
    }

    fun set360mockups(data360mockups: Model360mockups) {
        mockups.value = data360mockups
    }

    fun getModels(): LiveData<ModelModels> {
        return models
    }

    fun getPolitics():LiveData<ModelPolitics>{
        return  politics
    }

    fun get360mockups():LiveData<Model360mockups>{
        return  mockups
    }
}