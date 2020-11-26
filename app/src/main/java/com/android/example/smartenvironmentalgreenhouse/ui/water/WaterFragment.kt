package com.android.example.smartenvironmentalgreenhouse.ui.water

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.example.smartenvironmentalgreenhouse.R

class WaterFragment : Fragment() {

    private lateinit var waterViewModel: WaterViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        waterViewModel =
                ViewModelProvider(this).get(WaterViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_water, container, false)
//        val textView: TextView = root.findViewById(R.id.text_water)
//        waterViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }
}