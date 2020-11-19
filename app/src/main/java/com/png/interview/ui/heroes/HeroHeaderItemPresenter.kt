package com.png.interview.ui.heroes

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.png.interview.R
import com.png.interview.api.models.heroes.Hero
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class HeroHeaderItemPresenter
@Inject constructor(
    private val activity: AppCompatActivity
) {

    private var hero: Hero? = null

    fun bind(item: Hero?) {
        if (item != null) {
            this.hero = item
        }
    }

    val name
        get() = hero?.name

    val imageUrl
        get() = hero?.icon_url?.bigUrl

    val contentDescription
        get() = "image of $hero?.name"

    val placeholder: Drawable?
        get() = ContextCompat.getDrawable(activity, R.drawable.hots_placeholder)

    val type
        get() = hero?.type
}
