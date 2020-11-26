package com.android.example.smartenvironmentalgreenhouse.ui.temperature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TemperatureViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Temperature Fragment"
    }
    val text: LiveData<String> = _text
}