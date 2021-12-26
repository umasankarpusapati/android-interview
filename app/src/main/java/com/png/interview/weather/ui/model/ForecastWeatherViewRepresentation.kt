package com.png.interview.weather.ui.model

sealed class ForecastWeatherViewRepresentation {
    class ForecastWeatherViewRep(val data: ForecastWeatherViewData) : ForecastWeatherViewRepresentation()
    object Empty : ForecastWeatherViewRepresentation()
    object Error : ForecastWeatherViewRepresentation()
}
