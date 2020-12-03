package com.android.example.smartenvironmentalgreenhouse.ui.water

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.example.smartenvironmentalgreenhouse.R
import com.android.example.smartenvironmentalgreenhouse.databinding.FragmentWaterBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import kotlinx.coroutines.runBlocking

class WaterFragment : Fragment() {
    var waterValue = 0
    var soilValue = 0
    var requiredWaterLvl = 100
    var minSoilLvl = 300
    var bestSoilValue = 600
    private lateinit var binding: FragmentWaterBinding
    val third = Firebase.app("secondary")
    val thirdDatabase = Firebase.database(third).reference

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = FragmentWaterBinding.inflate(inflater, container, false)
        val view = binding.root
//
//        upDateData()
//
//        binding.buttonStart.setOnClickListener{
//            checkSoil()
//        }
//
//        binding.buttonStop.setOnClickListener{
//            stopProcess()
//        }
        return view
    }

//    fun upDateData(){
//        thirdDatabase.child("WaterLevel").child("waterLvl").addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                val w = dataSnapshot.getValue(String::class.java)
//                waterValue = w.toString().replace(" cm", "").toInt()
//                binding.textViewWaterLevel.setText(""+ waterValue+" cm")
//
//                Log.d("WaterLvl", "Value is: $w")
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.w("WaterLvl", "Failed to read value.", error.toException())
//            }
//        })
//
//        thirdDatabase.child("PI_001").child("mois").addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                val soilV = dataSnapshot.getValue<String>()
//                //test.setText(soilV)
//                soilValue = soilV.toString().replace("", "").toInt()
//                binding.textViewSoilLevel.setText(""+ soilValue+" %")
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.w("WaterLvl", "Failed to read value.", error.toException())
//            }
//        })
//    }
//
//    fun checkSoil(){
//        val s : Int = binding.textViewSoilLevel.text.toString().replace(" %", "").toInt()
//        if (s < minSoilLvl)
//            checkWater()
//        else
//            binding.textViewStatus.setText("Good condition")
//    }
//
//    fun checkWater(){
//        if (waterValue<requiredWaterLvl){
//            fillWater()
//        }
//
//    }
//    fun fillWater() = runBlocking {
//        binding.textViewStatus.setText("Water level low: Filling water")
//        waterRunnable.run()
//    }
//
//    fun waterPlants() {
//        binding.textViewStatus.setText("Soil moisture level low: Watering Plants")
//        wRunnable.run()
//    }
//
//
//    private val mHandler = Handler()
//    private val waterRunnable: Runnable = object : Runnable {
//        override fun run() {
//            thirdDatabase.child("PI_001").child("relay2").setValue("1")
//            if(waterValue< requiredWaterLvl){
//                waterValue++
//                binding.textViewWaterLevel.setText("" + waterValue + " cm")
//                thirdDatabase.child("WaterLevel").child("waterLvl").setValue("" + waterValue + " cm")
//                mHandler.postDelayed(this, 1000)
//            }
//            else{
//                thirdDatabase.child("PI_001").child("relay2").setValue("0")
//                mHandler.removeCallbacks(this)
//                waterPlants()
//            }
//        }
//    }
//
//    private val wHandler = Handler()
//    fun stopProcess(){
//        thirdDatabase.child("PI_001").child("relay1").setValue("0")
//        wHandler.removeCallbacks(wRunnable)
//        binding.textViewStatus.setText("Good condition")
//    }
//
//    private val wRunnable: Runnable = object : Runnable {
//        override fun run() {
//            val s : Int = binding.textViewSoilLevel.text.toString().replace(" %", "").toInt()
//            thirdDatabase.child("PI_001").child("relay1").setValue("1")
//            if(s< bestSoilValue){
//                waterValue--
//                binding.textViewWaterLevel.setText("" + waterValue + " cm")
//                thirdDatabase.child("WaterLevel").child("waterLvl").setValue("" + waterValue + " cm")
//                wHandler.postDelayed(this, 1000)
//            }else{
//                thirdDatabase.child("PI_001").child("relay1").setValue("0")
//                wHandler.removeCallbacks(this)
//                binding.textViewStatus.setText("Good condition")
//            }
//        }
//    }
}