package com.png.interview.ui.about

import android.content.res.Resources
import androidx.databinding.BaseObservable
import com.png.interview.R
import com.png.interview.api.models.heroes.Hero
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class AboutViewBinder
@Inject constructor(
    val resources: Resources
) : BaseObservable() {

    val aboutText = resources.getString(R.string.about_content)

}