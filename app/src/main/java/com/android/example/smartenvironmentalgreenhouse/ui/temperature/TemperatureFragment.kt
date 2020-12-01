package com.android.example.smartenvironmentalgreenhouse.ui.temperature

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.example.smartenvironmentalgreenhouse.R
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import com.google.firebase.ktx.initialize


class TemperatureFragment : Fragment() {

    private lateinit var temperatureViewModel: TemperatureViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        temperatureViewModel =
                ViewModelProvider(this).get(TemperatureViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_temperature, container, false)

        val database = Firebase.database
        val myRef = database.reference
        val tempValue: TextView = root.findViewById(R.id.tempValue)
        val hmdValue: TextView = root.findViewById(R.id.hmdValue)
        val fanStatus: TextView = root.findViewById(R.id.fanStatus)
        val lightStatus: TextView = root.findViewById(R.id.lightStatus)
        val waterSprayStatus: TextView = root.findViewById(R.id.waterSprayStatus)
        val windowStatus: TextView = root.findViewById(R.id.windowStatus)

        val onButton: Button = root.findViewById(R.id.openWindow)
        val offButton: Button = root.findViewById(R.id.closeWindow)

        val secondary = Firebase.app("secondary")
        val secondaryDatabase = Firebase.database(secondary)
        val myRef2 = secondaryDatabase.reference

        myRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val fan = snapshot.child("PI_04_CONTROL").child("relay1").value.toString()
                val light = snapshot.child("PI_04_CONTROL").child("relay2").value.toString()
                val water = snapshot.child("PI_04_CONTROL").child("lcdscr").value.toString()
                val window = snapshot.child("PI_04_CONTROL").child("oledsc").value.toString()

                //Show status
                if (fan == "1")
                    fanStatus.text = "Fan Status: On"
                else
                    fanStatus.text = "Fan Status: Off"

                if (light == "1")
                    lightStatus.text = "Light Status: On"
                else
                    lightStatus.text = "Light Status: Off"

                if (water == "1")
                    waterSprayStatus.text = "Water Spray Status: On"
                else
                    waterSprayStatus.text = "Water Spray Status: Off"

                if (window == "1")
                    windowStatus.text = "Window Status: On"
                else
                    windowStatus.text = "Window Status: Off"
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }

        })

        myRef2.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val tempeValue = dataSnapshot.child("Temperature").child("tempeLvl").value.toString()
                val humidValue = dataSnapshot.child("Humidity").child("humidLvl").value.toString()

                var currentTempe = tempeValue.filter { it.isDigit() }
                var currentHumid = humidValue.filter { it.isDigit() }

                var currentTempeInt: Int = currentTempe.toInt()
                var currentHumidInt: Int = currentHumid.toInt()

                //processTempe(currentTempeInt)
                //processHumid(currentHumidInt)

                Log.d(TAG, "Value is: $currentTempeInt")
                Log.d(TAG, "Value is: $currentHumidInt")

                tempValue.text = tempeValue
                hmdValue.text = humidValue

            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })

        onButton.setOnClickListener {
            myRef.child("PI_04_CONTROL").child("oledsc").setValue("1")
        }

        offButton.setOnClickListener {
            myRef.child("PI_04_CONTROL").child("oledsc").setValue("0")
        }

        return root
    }

    private fun processTempe(temperature: Int) {
        val database = Firebase.database
        if (temperature > 27)
        //High temperature, turn on the fan
            database.reference.child("PI_04_CONTROL").child("relay1").setValue("1")
        else if (temperature < 20)
        //Low tempreture, turn on the light
            database.reference.child("PI_04_CONTROL").child("relay2").setValue("1")
        else {
            //Desire temperature
            database.reference.child("PI_04_CONTROL").child("relay1").setValue("0")
            database.reference.child("PI_04_CONTROL").child("relay2").setValue("0")
        }
    }

    private fun processHumid(humidity: Int) {
        val database = Firebase.database
        if (humidity > 10)
        //High humidity, open the window
            database.reference.child("PI_04_CONTROL").child("oledsc").setValue("1")
        else if (humidity < 8)
        //Low humidity, turn on the water spray
            database.reference.child("PI_04_CONTROL").child("lcdscr").setValue("1")
        else {
            //Ideal humidity
            database.reference.child("PI_04_CONTROL").child("oledsc").setValue("0")
            database.reference.child("PI_04_CONTROL").child("lcdscr").setValue("0")
        }
    }

}