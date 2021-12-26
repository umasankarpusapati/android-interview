package com.png.interview.weather.domain

import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.ui.model.AvailableWeatherViewData
import com.png.interview.weather.ui.model.CurrentWeatherViewRepresentation
import com.png.interview.weather.ui.viewmodel.IMPERIAL
import javax.inject.Inject

interface CreateCurrentWeatherRepFromQueryUseCase {
    suspend operator fun invoke(query: String, units: Int): CurrentWeatherViewRepresentation
}

class DefaultCreateCurrentWeatherRepFromQueryUseCase @Inject constructor(
    private val getCurrentWeatherDataUseCase: GetCurrentWeatherDataUseCase
) : CreateCurrentWeatherRepFromQueryUseCase {
    override suspend fun invoke(query: String, units: Int): CurrentWeatherViewRepresentation {
        return when (val result = getCurrentWeatherDataUseCase(query)) {
            is NetworkResponse.Success -> {
                CurrentWeatherViewRepresentation.AvailableWeatherViewRep(
                    if (units == IMPERIAL) {
                        AvailableWeatherViewData(
                            name = result.body.location.name,
                            date = result.body.location.localtime,
                            temperature = "${result.body.current.temp_f} F",
                            condition = result.body.current.condition.text,
                            windDirection = result.body.current.wind_dir,
                            windSpeed = "${result.body.current.gust_mph} MPH"
                        )
                    } else {
                        AvailableWeatherViewData(
                            name = result.body.location.name,
                            date = result.body.location.localtime,
                            temperature = "${result.body.current.temp_c} C",
                            condition = result.body.current.condition.text,
                            windDirection = result.body.current.wind_dir,
                            windSpeed = "${result.body.current.gust_kph} KPH"
                        )
                    }
                )
            }
            else -> {
                CurrentWeatherViewRepresentation.Error
            }
        }
    }
}