package com.png.interview.weather.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.png.interview.R
import com.png.interview.weather.domain.CreateAutocompleteRepFromQueryUseCase
import com.png.interview.weather.domain.CreateCurrentWeatherRepFromQueryUseCase
import com.png.interview.weather.ui.model.AutocompleteViewData
import com.png.interview.weather.ui.model.AutocompleteViewRepresentation
import com.png.interview.weather.ui.model.CurrentWeatherViewRepresentation
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrentWeatherViewModel @Inject constructor(
    private val createCurrentWeatherRepFromQueryUseCase: CreateCurrentWeatherRepFromQueryUseCase,
    private val createAutocompleteRepFromQueryUseCase: CreateAutocompleteRepFromQueryUseCase
) : ViewModel() {

    private val _currentWeatherViewRepresentation = MutableStateFlow<CurrentWeatherViewRepresentation>(CurrentWeatherViewRepresentation.Empty)
    private val _autocompleteViewRepresentation = MutableStateFlow<AutocompleteViewRepresentation>(AutocompleteViewRepresentation.Empty)

    fun submitCurrentWeatherSearch(query: String, units: Int) {
        viewModelScope.launch {
            _currentWeatherViewRepresentation.value = createCurrentWeatherRepFromQueryUseCase(query, units)
        }
    }

    fun submitAutocompleteSearch(query: String) {
        viewModelScope.launch {
            _autocompleteViewRepresentation.value = createAutocompleteRepFromQueryUseCase(query)
        }
    }

    fun submitEmptyAutocompleteSearch() {
        _autocompleteViewRepresentation.value = AutocompleteViewRepresentation.Empty
    }

    val availableCurrentWeatherLiveData =
        _currentWeatherViewRepresentation
            .map { (it as? CurrentWeatherViewRepresentation.AvailableWeatherViewRep)?.data }
            .asLiveData()

    val autocompleteLiveData =
        _autocompleteViewRepresentation
            .map { (it as? AutocompleteViewRepresentation.AutocompleteViewRep)?.data }
            .filter { it?.autocompleteData?.isNotEmpty() ?: false }
            .map {
                if (it?.autocompleteData?.size ?: 0 > 5) AutocompleteViewData(it?.autocompleteData?.subList(0, 5) ?: emptyList())
                else it
            }
            .asLiveData()

    val autocompleteVisibility =
        _autocompleteViewRepresentation
            .map { (it as? AutocompleteViewRepresentation.AutocompleteViewRep)?.data?.autocompleteData?.isNotEmpty() ?: false }
            .asLiveData()

    val message =
        combine(
            _currentWeatherViewRepresentation
                .filterNot { it is CurrentWeatherViewRepresentation.Empty }
                .map { it is CurrentWeatherViewRepresentation.Error },
            _autocompleteViewRepresentation
                .filterNot { it is AutocompleteViewRepresentation.Empty }
                .map { it is AutocompleteViewRepresentation.Error }
        ) { currentWeatherError: Boolean, autocompleteError: Boolean ->
            currentWeatherError || autocompleteError
        }
            .map {
                if (it) R.string.error_occurred_message
                else R.string.empty_message
            }
            .onStart { emit(R.string.please_enter_zip_or_city) }
            .asLiveData()
}