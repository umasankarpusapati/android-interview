package com.png.interview.weather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.png.interview.databinding.FragmentForecastWeatherBinding
import com.png.interview.ui.InjectedFragment
import com.png.interview.weather.ui.binder.ForecastWeatherFragmentViewBinder

class ForecastWeatherFragment : InjectedFragment() {

    private val args: ForecastWeatherFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentForecastWeatherBinding.inflate(inflater, container, false).apply {
            viewBinder = ForecastWeatherFragmentViewBinder(
                getViewModel(),
                getViewModel(),
                args.input
            )
            this.lifecycleOwner = viewLifecycleOwner
        }.root
    }
}