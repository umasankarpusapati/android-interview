package com.png.interview.weather.di

import android.app.Activity
import com.png.interview.weather.domain.*
import dagger.Binds
import dagger.Module

@Module
abstract class WeatherUseCaseModule {

    @Binds
    abstract fun bindsGetCurrentWeatherDataUseCase(useCase: DefaultGetCurrentWeatherDataUseCase): GetCurrentWeatherDataUseCase

    @Binds
    abstract fun bindsGetCurrentWeatherRepUseCase(useCase: DefaultCreateCurrentWeatherRepFromQueryUseCase): CreateCurrentWeatherRepFromQueryUseCase

    @Binds
    abstract fun bindsGetForecastWeatherDataUseCase(useCase: DefaultGetForecastWeatherDataUseCase): GetForecastWeatherDataUseCase

    @Binds
    abstract fun bindsGetForecastWeatherRepUseCase(useCase: DefaultCreateForecastWeatherRepFromQueryUseCase): CreateForecastWeatherRepFromQueryUseCase

    @Binds
    abstract fun bindsGetAutocompleteDataUseCase(useCase: DefaultGetAutocompleteDataUseCase): GetAutocompleteDataUseCase

    @Binds
    abstract fun bindsGetAutocompleteRepUseCase(useCase: DefaultCreateAutocompleteRepFromQueryUseCase): CreateAutocompleteRepFromQueryUseCase
}