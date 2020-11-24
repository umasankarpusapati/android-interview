package com.png.interview.ui.heroes

import androidx.databinding.BaseObservable
import com.png.interview.api.models.heroes.Hero
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class HeroesFragmentViewBinder
@Inject constructor(
    val adapter: HeroesAdapter
) : BaseObservable() {
    fun bind(it: List<Hero>) {
        adapter.bindData(it)
    }
}