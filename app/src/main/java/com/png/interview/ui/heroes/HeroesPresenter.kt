package com.png.interview.ui.heroes

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import com.png.interview.api.models.heroes.Hero
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class HeroesPresenter
@Inject constructor(
    val adapter: HeroesAdapter
) : BaseObservable() {
    fun bind(it: List<Hero>) {
        adapter.bindData(it)
    }
}