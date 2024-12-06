package com.example.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

data class HourlyWeather(
    val time: String,
    val iconResId: Int,
    val temperature: String,
    val isNow: Boolean = false // Hozirgi vaqtni belgilash uchun
)

class HourlyWeatherAdapter(private val hourlyWeatherList: List<HourlyWeather>) :
    RecyclerView.Adapter<HourlyWeatherAdapter.HourlyWeatherViewHolder>() {

    class HourlyWeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
        val weatherIconImageView: ImageView = itemView.findViewById(R.id.weatherIconImageView)
        val temperatureTextView: TextView = itemView.findViewById(R.id.temperatureTextView)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyWeatherViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card_weather, parent, false)
        return HourlyWeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: HourlyWeatherViewHolder, position: Int) {
        val weather = hourlyWeatherList[position]
        holder.timeTextView.text = if (weather.isNow) "Now" else weather.time
        holder.weatherIconImageView.setImageResource(weather.iconResId)
        holder.temperatureTextView.text = weather.temperature

        // Orqa fonni belgilash
        if (weather.isNow) {
            holder.cardView.setCardBackgroundColor(holder.itemView.context.getColor(R.color.now_background))
        } else {
            holder.cardView.setCardBackgroundColor(holder.itemView.context.getColor(R.color.default_card_background))
        }
    }

    override fun getItemCount() = hourlyWeatherList.size
}
