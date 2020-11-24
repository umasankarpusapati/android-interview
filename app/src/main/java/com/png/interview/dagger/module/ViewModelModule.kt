package com.png.interview.dagger.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.png.interview.dagger.viewmodel.ViewModelFactory
import com.png.interview.dagger.viewmodel.ViewModelKey
import com.png.interview.ui.about.AboutViewModel
import com.png.interview.ui.heroes.HeroDetailFragmentViewModel
import com.png.interview.ui.heroes.HeroesFragmentViewModel
import com.png.interview.ui.maps.MapsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AboutViewModel::class)
    internal abstract fun aboutViewModel(viewModel: AboutViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HeroesFragmentViewModel::class)
    internal abstract fun heroesViewModel(viewModel: HeroesFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HeroDetailFragmentViewModel::class)
    internal abstract fun heroesDetailsViewModel(viewModel: HeroDetailFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MapsViewModel::class)
    internal abstract fun mapsViewModel(viewModel: MapsViewModel): ViewModel
}
