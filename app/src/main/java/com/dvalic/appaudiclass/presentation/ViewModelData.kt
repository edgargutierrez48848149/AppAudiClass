package com.dvalic.appaudiclass.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dvalic.appaudiclass.data.models.ModelModels
import com.dvalic.appaudiclass.data.models.ModelPolitics

class ViewModelData : ViewModel() {
    private val models = MutableLiveData<ModelModels>()
    private val politics = MutableLiveData<ModelPolitics>()

    fun setModels(dataModels: ModelModels) {
        models.value = dataModels
    }

    fun setPolitics(dataPolitics: ModelPolitics) {
        politics.value = dataPolitics
    }

    fun getModels(): LiveData<ModelModels> {
        return models
    }

    fun getPolitics():LiveData<ModelPolitics>{
        return  politics
    }
}