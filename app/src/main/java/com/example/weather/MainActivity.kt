package com.example.weather

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // BottomSheetFragment ko'rsatish uchun umumiy funksiya
        val showBottomSheet = {
            val bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }

        // Barcha tegishli elementlar uchun BottomSheet o'rnatish
        val clickableViews = listOf(
            binding.textView2,
            binding.textView5,
            binding.weeklyTextView,
            binding.linearLayout,
            binding.view,
            binding.menuBN,
            binding.listBn
        )

        clickableViews.forEach { view ->
            view.setOnClickListener { showBottomSheet() }
        }

        // Soatlik ob-havo ro'yxatini yaratish
        val hourlyWeatherList = listOf(
            HourlyWeather("12 AM", R.drawable.weather_icon2, "19°"),
            HourlyWeather("1 AM", R.drawable.weather_icon1, "19°", isNow = true), // Hozirgi vaqt
            HourlyWeather("2 AM", R.drawable.weather_icon3, "19°"),
            HourlyWeather("3 AM", R.drawable.weather_icon2, "20°"),
            HourlyWeather("4 AM", R.drawable.weather_icon2, "21°"),
            HourlyWeather("5 AM", R.drawable.weather_icon2, "22°")
        )

        // RecyclerView sozlash
        setupRecyclerView(hourlyWeatherList)
    }

    private fun setupRecyclerView(hourlyWeatherList: List<HourlyWeather>) {
        binding.recyclerView2.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = HourlyWeatherAdapter(hourlyWeatherList)
        }
    }
}
