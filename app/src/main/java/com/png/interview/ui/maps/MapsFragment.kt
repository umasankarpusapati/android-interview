package com.png.interview.ui.maps

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.png.interview.databinding.FragmentMapsBinding
import com.png.interview.ui.InjectedFragment

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
