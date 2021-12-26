package com.png.interview.weather.domain

import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.api.model.Forecast
import com.png.interview.weather.ui.model.ForecastWeatherData
import com.png.interview.weather.ui.model.ForecastWeatherViewData
import com.png.interview.weather.ui.model.ForecastWeatherViewRepresentation
import com.png.interview.weather.ui.viewmodel.IMPERIAL
import javax.inject.Inject

interface CreateForecastWeatherRepFromQueryUseCase {
    suspend operator fun invoke(query: String, units: Int): ForecastWeatherViewRepresentation
}

class DefaultCreateForecastWeatherRepFromQueryUseCase @Inject constructor(
    private val getForecastWeatherDataUseCase: GetForecastWeatherDataUseCase
) : CreateForecastWeatherRepFromQueryUseCase {
    override suspend fun invoke(query: String, units: Int): ForecastWeatherViewRepresentation {
        return when (val result = getForecastWeatherDataUseCase(query)) {
            is NetworkResponse.Success -> {
                ForecastWeatherViewRepresentation.ForecastWeatherViewRep(
                    getForecastWeatherDataList(result.body.forecast, units)
                )
            }
            else -> {
                ForecastWeatherViewRepresentation.Error
            }
        }
    }

    private fun getForecastWeatherDataList(forecast: Forecast, units: Int): ForecastWeatherViewData {
        return if (units == IMPERIAL) {
            (forecast.forecastday.indices).map {
                ForecastWeatherData(
                    date = forecast.forecastday[it].date,
                    minTemp = "${forecast.forecastday[it].day.mintemp_f} F",
                    maxTemp = "${forecast.forecastday[it].day.maxtemp_f} F",
                    windSpeed = "${forecast.forecastday[it].day.maxwind_mph} MPH",
                    condition = forecast.forecastday[it].day.condition.text
                )
            }
        } else {
            (forecast.forecastday.indices).map {
                ForecastWeatherData(
                    date = forecast.forecastday[it].date,
                    minTemp = "${forecast.forecastday[it].day.mintemp_c} C",
                    maxTemp = "${forecast.forecastday[it].day.maxtemp_c} C",
                    windSpeed = "${forecast.forecastday[it].day.maxwind_kph} KPH",
                    condition = forecast.forecastday[it].day.condition.text
                )
            }
        }.toList().run { ForecastWeatherViewData(this) }
    }
}