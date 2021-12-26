package com.png.interview.weather.ui.binder

import android.text.Editable
import androidx.databinding.ObservableField
import com.png.interview.R
import com.png.interview.weather.ui.viewmodel.CurrentWeatherViewModel
import com.png.interview.weather.ui.viewmodel.SettingsViewModel

class CurrentWeatherFragmentViewBinder(
    private val viewModel: CurrentWeatherViewModel,
    private val settingsViewModel: SettingsViewModel,
    private val toastAction: (Int) -> Unit,
    private val settingsAction: () -> Unit,
    private val forecastWeatherAction: (String) -> Unit
) {

    val input = ObservableField("")
    val availableWeatherViewData = viewModel.availableCurrentWeatherLiveData
    val message = viewModel.message
    val autocompleteViewData = viewModel.autocompleteLiveData
    val showAutocomplete = viewModel.autocompleteVisibility
    val submitSearch: (String) -> Unit = { query -> submitSearch(query) }

    private var avoidCallingAfterInputTextChanged = true

    fun refreshClicked() {
        toastAction(R.string.refreshing_climate_data)
        viewModel.submitCurrentWeatherSearch(settingsViewModel.getInput(), settingsViewModel.getUnits())
    }

    fun seeForecastClicked() {
        forecastWeatherAction(settingsViewModel.getInput())
    }

    fun settingsClicked() {
        settingsAction()
    }

    fun afterInputTextChanged(value: Editable) {
        if (avoidCallingAfterInputTextChanged) {
            avoidCallingAfterInputTextChanged = false
            return
        }
        if (value.length >= 3) {
            viewModel.submitAutocompleteSearch(input.get() ?: "")
        } else {
            viewModel.submitEmptyAutocompleteSearch()
        }
    }

    fun goClicked() {
        val query = input.get() ?: ""
        when {
            query.isEmpty() -> toastAction(R.string.please_enter_query)
            query.length < 3 -> toastAction(R.string.please_enter_more_characters)
            else -> submitSearch(query)
        }
    }

    private fun submitSearch(query: String) {
        settingsViewModel.saveInput(query)
        input.set("")
        viewModel.submitEmptyAutocompleteSearch()
        viewModel.submitCurrentWeatherSearch(query, settingsViewModel.getUnits())
    }
}