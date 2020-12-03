package com.android.example.smartenvironmentalgreenhouse.ui.light



import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.example.smartenvironmentalgreenhouse.R
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class LightFragment : Fragment(), SensorEventListener {

    private lateinit var lightViewModel: LightViewModel
    private lateinit var sensorManager: SensorManager
    private var light: Sensor? = null
    private var manualMode1 : Boolean = false
    private var manualMode2 : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        lightViewModel =
                ViewModelProvider(this).get(LightViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_light, container, false)

        val textManual1: TextView = root.findViewById(R.id.textViewManual1)
        val textManual2: TextView = root.findViewById(R.id.textViewManual2)
        val test: TextView = root.findViewById(R.id.textViewLightValue)

        val database = Firebase.database
        val myRef = database.reference

        val buttonManualMode1 = root.findViewById<Button>(R.id.buttonManualMode1)
        val buttonManualMode2 = root.findViewById<Button>(R.id.buttonManualMode2)

        val buttonOn1 = root.findViewById<Button>(R.id.buttonOn1)
        val buttonOff1 = root.findViewById<Button>(R.id.buttonOff1)

        val buttonOn2 = root.findViewById<Button>(R.id.buttonOn2)
        val buttonOff2 = root.findViewById<Button>(R.id.buttonOff2)

        val relay1: TextView = root.findViewById(R.id.textViewRelay1)
        val relay2: TextView = root.findViewById(R.id.textViewRelay2)
        if (manualMode1)
        {
            textManual1.text = " : Manual Mode"
        }
        else
        {
            textManual1.text = " : Auto Mode"
        }

        if (manualMode2)
        {
            textManual2.text = " : Manual Mode"
        }
        else
        {
            textManual2.text = " : Auto Mode"
        }


        buttonManualMode1.setOnClickListener() {
            manualMode1 = !manualMode1
            if (manualMode1)
            {
                textManual1.text = " : Manual Mode"
            }
            else
            {
                textManual1.text = " : Auto Mode"
            }
        }

        buttonManualMode2.setOnClickListener() {
            manualMode2 = !manualMode2
            if (manualMode2)
            {
                textManual2.text = " : Manual Mode"
            }
            else
            {
                textManual2.text = " : Auto Mode"
            }
        }

        buttonOn1.setOnClickListener() {
            if (manualMode1)
            {
                val database = Firebase.database
                database.reference.child("PI_04_CONTROL").child("relay1").setValue("1")
                relay1.text = "ON"
            }
            else
            {
                Toast.makeText(context, "Auto Mode is still turned ON!", Toast.LENGTH_SHORT).show()
            }
        }

        buttonOff1.setOnClickListener() {
            if (manualMode1)
            {
                val database = Firebase.database
                database.reference.child("PI_04_CONTROL").child("relay1").setValue("0")
                relay1.text = "OFF"
            }
            else
            {
                Toast.makeText(context, "Auto Mode is still turned ON!", Toast.LENGTH_SHORT).show()
            }
        }

        buttonOn2.setOnClickListener() {
            if (manualMode2)
            {
                val database = Firebase.database
                database.reference.child("PI_04_CONTROL").child("relay2").setValue("1")
                relay2.text = "ON"
            }
            else
            {
                Toast.makeText(context, "Auto Mode is still turned ON!", Toast.LENGTH_SHORT).show()
            }
        }

        buttonOff2.setOnClickListener() {
            if (manualMode2)
            {
                val database = Firebase.database
                database.reference.child("PI_04_CONTROL").child("relay2").setValue("0")
                relay2.text = "OFF"
            }
            else
            {
                Toast.makeText(context, "Auto Mode is still turned ON!", Toast.LENGTH_SHORT).show()
            }
        }







        /*val c = Calendar.getInstance()
        val month = c.get(Calendar.MONTH) + 1
        val day = c.get(Calendar.DAY_OF_MONTH)
        val hour :String = c.get(Calendar.HOUR_OF_DAY).toString()

        val childName = "PI_04_2020$month$day"
        val hourName = "$hour"

        val mHandler = Handler()




        Log.i("childName","$childName")
        Log.i("hourName","$hourName")

        myRef.setValue("=App II running=")

        myRef.child("PI_04_2020$month$day").child("$hour").addChildEventListener(object :
            ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                val energyUsage = rand(700,1000)
                val test: TextView = root.findViewById(R.id.textViewLightValue)
                val test2: TextView = root.findViewById(R.id.textViewEnergyValue)
                val test3: TextView = root.findViewById(R.id.textViewEnergyGenerated)

                val relay1: TextView = root.findViewById(R.id.textViewRelay1)
                val relay2: TextView = root.findViewById(R.id.textViewRelay2)



                val lightLevel = dataSnapshot.child("light").value.toString()
                val relay1Val :String
                val relay2Val :String

                test.text = lightLevel
                test2.text = energyUsage.toString()
                test3.text = (lightLevel.toInt()*6).toString()
                Log.i("Light Value","$lightLevel")



                relay1.text = processLight(lightLevel)

                relay2.text = processEnergyConsumption(energyUsage,lightLevel)




            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {}

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}

            override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {}

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("child", "postComments:onCancelled", databaseError.toException())
            }
        })
        val textView: TextView = root.findViewById(R.id.text_light)
        lightViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return root
    }


    private fun processLight(light: String): String{
        val database = Firebase.database
        val value :String = if (light.toFloat() <5)
        //Low light level, turn on led
            "1"
        else{
            "0"
        }
        database.reference.child("PI_04_CONTROL").child("relay1").setValue(value)
        return value
    }

    private fun processEnergyConsumption(energyConsumption: Int,light :String) : String{
        val database = Firebase.database
        val value :String = if (energyConsumption > light.toFloat()* 60)
        //Low light level, turn on led
            "1"
        else{

            "0"

        }
        database.reference.child("PI_04_CONTROL").child("relay2").setValue(value)
        return value
    }





    private fun rand(start: Int, end: Int): Int {
        require(start <= end) { "Illegal Argument" }
        return (start..end).random()
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val test : TextView = requireView().findViewById(R.id.textViewLightValue)
        if (event != null) {
            val energyUsage = rand(700,1000)
            val test: TextView = requireView().findViewById(R.id.textViewLightValue)
            val test2: TextView = requireView().findViewById(R.id.textViewEnergyValue)
            val test3: TextView = requireView().findViewById(R.id.textViewEnergyGenerated)


            val relay1: TextView = requireView().findViewById(R.id.textViewRelay1)
            val relay2: TextView = requireView().findViewById(R.id.textViewRelay2)




            test2.text = energyUsage.toString()
            test3.text = (event.values[0]*60).toString()
            if (!manualMode1)
            {

                relay1.text = (if ( processLight(event.values[0].toString()) == "1") {
                    "ON"
                }
                else
                {
                    "OFF"
                }).toString()
            }

            if (!manualMode2)
            {

                relay2.text = (if (processEnergyConsumption(energyUsage,event.values[0].toString()) == "1") {
                    "ON"
                }
                else
                {
                    "OFF"
                }).toString()
            }

            test.text = event.values[0].toString()
        }
    }

    override fun onResume() {
        // Register a listener for the sensor.
        super.onResume()
        sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL)
    }


    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }



}