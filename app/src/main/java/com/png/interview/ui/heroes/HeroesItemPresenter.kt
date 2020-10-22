package com.png.interview.ui.heroes

import com.png.interview.api.models.heroes.Hero
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class HeroesItemPresenter
@Inject constructor() {

    private var hero: Hero? = null

    fun bind(item: Hero?) {
        if (item != null) {
            this.hero = item
        }
    }

    val name
        get() = hero?.name
}