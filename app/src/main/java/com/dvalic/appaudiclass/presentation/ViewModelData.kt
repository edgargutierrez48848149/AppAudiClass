package com.dvalic.appaudiclass.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dvalic.appaudiclass.data.models.ModelModels

class ViewModelData : ViewModel() {
    private val model = MutableLiveData<ModelModels>()

    fun setModel(models: ModelModels) {
        model.value = models
    }

    fun getModel(): LiveData<ModelModels> {
        return model
    }
}