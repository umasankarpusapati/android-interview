package com.png.interview.weather.ui.binder

import com.png.interview.weather.ui.viewmodel.ForecastWeatherViewModel
import com.png.interview.weather.ui.viewmodel.SettingsViewModel

class ForecastWeatherFragmentViewBinder(
    private val viewModel: ForecastWeatherViewModel,
    private val settingsViewModel: SettingsViewModel,
    private val input: String
) {

    var forecastWeatherViewData = viewModel.forecastWeatherLiveData

    init {
        viewModel.submitForecastWeatherSearch(input, settingsViewModel.getUnits())
    }

}