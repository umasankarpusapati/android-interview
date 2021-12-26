package com.png.interview.weather.ui.binder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.png.interview.R
import com.png.interview.weather.ui.model.ForecastWeatherData

class ForecastWeatherViewAdapter constructor(
    private var forecastWeatherDataList: List<ForecastWeatherData> = emptyList()
) : RecyclerView.Adapter<ForecastWeatherViewAdapter.ForecastWeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastWeatherViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_view_forecast_weather,
            parent,
            false
        )
        return ForecastWeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastWeatherViewHolder, position: Int) {
        holder.bind(forecastWeatherDataList[position])
    }

    override fun getItemCount(): Int {
        return forecastWeatherDataList.size
    }

    class ForecastWeatherViewHolder(
        private val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(forecastWeatherData: ForecastWeatherData) {
            binding.setVariable(BR.forecastWeatherData, forecastWeatherData)
        }
    }
}