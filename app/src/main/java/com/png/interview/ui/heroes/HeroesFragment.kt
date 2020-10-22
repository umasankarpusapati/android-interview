package com.png.interview.ui.heroes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.png.interview.R
import com.png.interview.databinding.FragmentHeroesBinding
import com.png.interview.extensions.onMain
import com.png.interview.extensions.viewLifecycleScope
import com.png.interview.ui.InjectedFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HeroesFragment : InjectedFragment() {

    companion object {
        fun newInstance() = HeroesFragment()
    }

    @Inject lateinit var presenter: HeroesPresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = FragmentHeroesBinding.inflate(inflater, container, false)
        binding.presenter = presenter
        binding.lifecycleOwner = viewLifecycleOwner

        val viewModel = getViewModel<HeroesViewModel>()
        viewLifecycleScope.launch(Dispatchers.IO) {
            viewModel.getHeroes().let {
                onMain {
                    presenter.bind(it)
                }
            }
        }
        return binding.root
    }

}
