package com.png.interview.ui.heroes

import com.png.interview.api.HotsApi
import com.png.interview.api.models.heroes.Hero
import com.png.interview.api.common_model.action.operations.onErrorReturn
import com.png.interview.api.common_model.action.toActionResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class RetrieveHeroesAction
@Inject constructor(
    private val hotsApi: HotsApi
) {
    suspend fun getHeroes(): List<Hero> =
        hotsApi.getAllHeroes().toActionResult(resultConversion = {
            it.sortedBy { it.name }
        })
            .onErrorReturn {
                emptyList()
            }.result
}