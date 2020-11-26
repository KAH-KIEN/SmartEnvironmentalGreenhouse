package com.android.example.smartenvironmentalgreenhouse.ui.light

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.example.smartenvironmentalgreenhouse.R

class LightFragment : Fragment() {

    private lateinit var lightViewModel: LightViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        lightViewModel =
                ViewModelProvider(this).get(LightViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_light, container, false)
//        val textView: TextView = root.findViewById(R.id.text_light)
//        lightViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }
}