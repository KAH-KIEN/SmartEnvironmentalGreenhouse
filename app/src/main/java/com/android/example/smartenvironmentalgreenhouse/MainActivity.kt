package com.android.example.smartenvironmentalgreenhouse

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.FirebaseOptions
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_light, R.id.navigation_water, R.id.navigation_temperature))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val options = FirebaseOptions.Builder()
                .setProjectId("bait2023-iot")
                .setApiKey("AIzaSyCJYdbDRNX6uvSl945e6kuWUqLFi217e94")
                .setApplicationId("1:570947060769:android:2d97f11855f393050d8f60")
                .setDatabaseUrl("https://bait2023-iot.firebaseio.com/")
                .build()

        Firebase.initialize(this , options, "secondary")
    }
}