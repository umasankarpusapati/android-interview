package com.png.interview.ui.maps

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.png.interview.R
import com.png.interview.databinding.FragmentHeroesBinding
import com.png.interview.databinding.FragmentMapsBinding
import com.png.interview.extensions.onMain
import com.png.interview.extensions.viewLifecycleScope
import com.png.interview.ui.InjectedFragment
import com.png.interview.ui.heroes.HeroesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MapsFragment : InjectedFragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = FragmentMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = MapsFragment()
    }

}
