package com.example.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.databinding.BottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            val bottomSheet =
                it.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let { sheet ->
                val behavior = BottomSheetBehavior.from(sheet)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
                behavior.isDraggable = true // Drag imkoniyatini yoqish
                behavior.peekHeight = 0 // Ekran bo'ylab kengaytirish
            }
        }
        val hourlyWeatherList = listOf(
            HourlyWeather("12 AM", R.drawable.weather_icon2, "19°"),
            HourlyWeather("1 AM", R.drawable.weather_icon1, "19°", isNow = true), // Hozirgi vaqt
            HourlyWeather("2 AM", R.drawable.weather_icon3, "19°"),
            HourlyWeather("3 AM", R.drawable.weather_icon2, "20°"),
            HourlyWeather("4 AM", R.drawable.weather_icon2, "21°"),
            HourlyWeather("5 AM", R.drawable.weather_icon2, "22°")
        )

        // RecyclerView sozlash
        binding.recyclerView2.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView2.adapter = HourlyWeatherAdapter(hourlyWeatherList)

    }
}
