package com.png.interview.ui.heroes

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class HeroesFragmentViewModel
@Inject constructor(private val heroesAction: RetrieveHeroesAction) : ViewModel() {
    suspend fun getHeroes() = heroesAction.getHeroes()
}
