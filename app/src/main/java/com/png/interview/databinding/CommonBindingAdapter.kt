package com.png.interview.databinding

import android.graphics.drawable.Drawable
import android.view.Gravity
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.png.interview.weather.ui.binder.AutocompleteViewAdapter
import com.png.interview.weather.ui.binder.ForecastWeatherViewAdapter
import com.png.interview.weather.ui.model.AutocompleteViewData
import com.png.interview.weather.ui.model.ForecastWeatherViewData

object CommonBindingAdapter {
    @BindingAdapter("visible")
    @JvmStatic
    fun setVisibility(view: View, visible: Boolean) {
        view.visibility = if (visible) VISIBLE else GONE
    }

    @JvmStatic
    @BindingAdapter("isStartGravity")
    fun setGravityStartAligned(view: TextView, isStartAligned: Boolean) {
        view.gravity = if (isStartAligned) {
            Gravity.START
        } else {
            Gravity.CENTER
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["coilSrc", "coilPlaceholder"], requireAll = false)
    fun setImageFromCoil(view: ImageView, imageUrl: String?, placeholder: Drawable?) {
        if (imageUrl != null) {
            view
                .load(imageUrl) {
                    placeholder(placeholder)
                    error(placeholder)
                }
        }
    }

    @JvmStatic
    @BindingAdapter("forecastWeatherViewData")
    fun setForecastWeatherViewData(view: RecyclerView, forecastWeatherViewData: ForecastWeatherViewData?) {
        forecastWeatherViewData?.forecastWeatherDataList?.apply {
            view.adapter = ForecastWeatherViewAdapter(this)
        }
    }

    @JvmStatic
    @BindingAdapter("autocompleteViewData", "submitSearch")
    fun setAutocompleteViewData(view: RecyclerView, autocompleteViewData: AutocompleteViewData?, submitSearch: (String) -> Unit) {
        autocompleteViewData?.autocompleteData?.apply {
            view.adapter = AutocompleteViewAdapter(this, submitSearch)
        }
    }
}
