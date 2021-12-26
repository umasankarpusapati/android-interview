package com.png.interview.weather.ui.binder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.png.interview.R

class AutocompleteViewAdapter(
    private val autocompleteDataList: List<String> = emptyList(),
    private val submitSearch: (String) -> Unit
) : RecyclerView.Adapter<AutocompleteViewAdapter.AutocompleteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutocompleteViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_view_autocomplete,
            parent,
            false
        )
        return AutocompleteViewHolder(binding, submitSearch)
    }

    override fun onBindViewHolder(holder: AutocompleteViewHolder, position: Int) {
        holder.bind(autocompleteDataList[position])
    }

    override fun getItemCount(): Int {
        return autocompleteDataList.size
    }

    class AutocompleteViewHolder(
        private val binding: ViewDataBinding,
        private val submitSearch: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(autocompleteData: String) {
            binding.setVariable(BR.autocompleteData, autocompleteData)
            binding.root.setOnClickListener { submitSearch(autocompleteData) }
        }
    }
}