package com.android.example.smartenvironmentalgreenhouse.ui.home

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.example.smartenvironmentalgreenhouse.R
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.internal.ContextUtils.getActivity
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    val CITY: String = "1735161"
    val API: String = "017e96941e773e4b05048f2b52e8bf2f"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        weatherTask().execute()

        return root
    }


    inner class weatherTask() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            /* Showing the ProgressBar, Making the main design GONE */
            getActivity()?.findViewById<ProgressBar>(R.id.loader)?.visibility = View.VISIBLE
            getActivity()?.findViewById<RelativeLayout>(R.id.mainContainer)?.visibility = View.GONE
            getActivity()?.findViewById<TextView>(R.id.errorText)?.visibility = View.GONE
        }

        override fun doInBackground(vararg params: String?): String? {
            var response: String?
            try {
                response =
                    URL("https://api.openweathermap.org/data/2.5/weather?id=$CITY&appid=$API&units=metric").readText(
                        Charsets.UTF_8
                    )
            } catch (e: Exception) {
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                /* Extracting JSON returns from the API */
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val wind = jsonObj.getJSONObject("wind")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
                val updatedAt: Long = jsonObj.getLong("dt")
                val updatedAtText =
                    "Updated at: " + SimpleDateFormat(
                        "dd/MM/yyyy hh:mm a",
                        Locale.ENGLISH
                    ).format(
                        Date(updatedAt * 1000)
                    )
                val temp = main.getString("temp") + "°C"
                val tempMin = "Min Temp: " + main.getString("temp_min") + "°C"
                val tempMax = "Max Temp: " + main.getString("temp_max") + "°C"
                val pressure = main.getString("pressure")
                val humidity = main.getString("humidity")
                val sunrise: Long = sys.getLong("sunrise")
                val sunset: Long = sys.getLong("sunset")
                val windSpeed = wind.getString("speed")
                val weatherDescription = weather.getString("description")
                val address = jsonObj.getString("name") + ", " + sys.getString("country")
                /* Populating extracted data into our views */
                getActivity()?.findViewById<TextView>(R.id.address)?.text = address
                getActivity()?.findViewById<TextView>(R.id.updated_at)?.text = updatedAtText
                getActivity()?.findViewById<TextView>(R.id.status)?.text =
                    weatherDescription.capitalize()
                getActivity()?.findViewById<TextView>(R.id.temp)?.text = temp
                getActivity()?.findViewById<TextView>(R.id.temp_min)?.text = tempMin
                getActivity()?.findViewById<TextView>(R.id.temp_max)?.text = tempMax
                getActivity()?.findViewById<TextView>(R.id.sunrise)?.text =
                    SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunrise * 1000))
                getActivity()?.findViewById<TextView>(R.id.sunset)?.text =
                    SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset * 1000))
                getActivity()?.findViewById<TextView>(R.id.wind)?.text = windSpeed
                getActivity()?.findViewById<TextView>(R.id.pressure)?.text = pressure
                getActivity()?.findViewById<TextView>(R.id.humidity)?.text = humidity
                /* Views populated, Hiding the loader, Showing the main design */
                getActivity()?.findViewById<ProgressBar>(R.id.loader)?.visibility = View.GONE
                getActivity()?.findViewById<RelativeLayout>(R.id.mainContainer)?.visibility =
                    View.VISIBLE
            } catch (e: Exception) {
                getActivity()?.findViewById<ProgressBar>(R.id.loader)?.visibility = View.GONE
                getActivity()?.findViewById<TextView>(R.id.errorText)?.visibility = View.VISIBLE
            }
        }
    }

}