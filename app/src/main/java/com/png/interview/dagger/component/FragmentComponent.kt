package com.png.interview.dagger.component

import com.png.interview.dagger.module.FragmentModule
import com.png.interview.dagger.scope.FragmentScope
import com.png.interview.ui.InjectedDialogFragment
import com.png.interview.ui.InjectedFragment
import com.png.interview.ui.about.AboutFragment
import com.png.interview.ui.heroes.HeroesFragment
import com.png.interview.ui.maps.MapsFragment
import dagger.Subcomponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
@FragmentScope
@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent {
    fun inject(heroesFragment: HeroesFragment)
    fun inject(mapsFragment: MapsFragment)
    fun inject(injectedFragment: InjectedFragment)
    fun inject(injectedDialogFragment: InjectedDialogFragment)
}
