package com.android.example.smartenvironmentalgreenhouse.ui.water

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WaterViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Water Fragment"
    }
    val text: LiveData<String> = _text
}