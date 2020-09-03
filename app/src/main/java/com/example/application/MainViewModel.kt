package com.example.application

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val viewStateLiveData = MutableLiveData<String>()
    private val model = MutableLiveData<String>()
    private var counter = 0

    init {
        viewStateLiveData.value = "Hello! "
    }
    fun getViewState(): LiveData<String> = viewStateLiveData

}


