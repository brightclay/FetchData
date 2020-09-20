package com.sighini.fetch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val vmData = MediatorLiveData<List<ModelData>>()
    private var modelLayer: ModelLayer
    var isLoading = true

    init {
        modelLayer = ModelLayer.getInstance(this.getApplication())
        var modelData = modelLayer.getData()

        vmData.addSource(modelData) { result: List<ModelData>? ->
            result?.let {
                isLoading = false
                vmData.value = it
            }
        }
    }
}