package com.png.interview.weather.ui.binder

import android.view.View
import com.png.interview.R
import com.png.interview.weather.ui.viewmodel.IMPERIAL
import com.png.interview.weather.ui.viewmodel.METRIC
import com.png.interview.weather.ui.viewmodel.SettingsViewModel

class SettingsFragmentViewBinder(
    private val viewModel: SettingsViewModel
) {

    val checkedButton =
        if (viewModel.getUnits() == IMPERIAL) R.id.rb_imperial
        else R.id.rb_metric

    fun onImperialSelected(view: View) {
        viewModel.updateUnits(IMPERIAL)
    }

    fun onMetricSelected(view: View) {
        viewModel.updateUnits(METRIC)
    }
}