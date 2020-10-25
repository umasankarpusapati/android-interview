package com.png.interview.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.png.interview.databinding.FragmentAboutBinding
import com.png.interview.ui.heroes.HeroesFragment
import javax.inject.Inject

class AboutFragment() : Fragment() {

    @Inject lateinit var presenter: AboutPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = FragmentAboutBinding.inflate(inflater, container, false)
        binding.presenter = presenter
        return binding.root
    }

    companion object {
        fun newInstance() = HeroesFragment()
    }
}
