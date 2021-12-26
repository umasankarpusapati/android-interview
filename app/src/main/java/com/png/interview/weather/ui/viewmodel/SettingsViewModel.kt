package com.png.interview.weather.ui.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import javax.inject.Inject

const val WEATHER_PREFERENCES = "WEATHER_PREFERENCES"
const val UNITS_PREFERENCES = "UNITS_PREFERENCES"
const val INPUT_PREFERENCES = "INPUT_PREFERENCES"
const val IMPERIAL = 0
const val METRIC = 1

class SettingsViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    fun updateUnits(units: Int) {
        sharedPreferences
            .edit()
            .putInt(UNITS_PREFERENCES, units)
            .apply()
    }

    fun getUnits(): Int {
        return sharedPreferences
            .getInt(UNITS_PREFERENCES, IMPERIAL)
    }

    fun saveInput(input: String) {
        sharedPreferences
            .edit()
            .putString(INPUT_PREFERENCES, input)
            .apply()
    }

    fun getInput(): String {
        return sharedPreferences
            .getString(INPUT_PREFERENCES, "") ?: ""
    }
}