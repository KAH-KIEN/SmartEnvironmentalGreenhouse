package com.android.example.smartenvironmentalgreenhouse.ui.light

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LightViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Light Fragment"
    }
    val text: LiveData<String> = _text
}