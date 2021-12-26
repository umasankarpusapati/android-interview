package com.png.interview.weather.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.png.interview.weather.domain.CreateForecastWeatherRepFromQueryUseCase
import com.png.interview.weather.ui.model.ForecastWeatherViewRepresentation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class ForecastWeatherViewModel @Inject constructor(
    private val createForecastWeatherRepFromQueryUseCase: CreateForecastWeatherRepFromQueryUseCase
) : ViewModel() {

    private val _forecastWeatherViewRepresentation = MutableStateFlow<ForecastWeatherViewRepresentation>(ForecastWeatherViewRepresentation.Empty)

    fun submitForecastWeatherSearch(query: String, units: Int) {
        viewModelScope.launch {
            _forecastWeatherViewRepresentation.value = createForecastWeatherRepFromQueryUseCase(query, units)
        }
    }

    val forecastWeatherLiveData =
        _forecastWeatherViewRepresentation
            .map { (it as? ForecastWeatherViewRepresentation.ForecastWeatherViewRep)?.data }
            .asLiveData()
}