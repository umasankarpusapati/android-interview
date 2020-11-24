package com.png.interview.ui.heroes

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.png.interview.api.models.heroes.Hero
import com.png.interview.databinding.DataboundViewHolder
import com.png.interview.databinding.ViewBasicHeroListItemBinding
import com.png.interview.extensions.getLayoutInflater
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject
import javax.inject.Provider

@FlowPreview
@ExperimentalCoroutinesApi
class HeroesAdapter
@Inject constructor(
    private val viewBinders: Provider<BasicHeroItemViewBinder>
) : RecyclerView.Adapter<DataboundViewHolder<ViewDataBinding>>() {

    var data = listOf<HeroAdapterItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataboundViewHolder<ViewDataBinding> {
        return DataboundViewHolder(
            ViewBasicHeroListItemBinding.inflate(parent.getLayoutInflater(), parent, false).apply {
                viewBinder = viewBinders.get()
            }
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: DataboundViewHolder<ViewDataBinding>, position: Int) {
        when (holder.binding) {
            is ViewBasicHeroListItemBinding -> {
                (data[position] as? HeroAdapterItem.HeroItem)?.hero.let {
                    holder.binding.viewBinder?.bind(it)
                }
            }
        }
        holder.binding.invalidateAll()
    }

    fun bindData(heroes: List<Hero>) {
        this.data = heroes.map { HeroAdapterItem.HeroItem(it) }
        notifyDataSetChanged()
    }

    sealed class HeroAdapterItem {
        data class HeroItem(val hero: Hero) : HeroAdapterItem()
        object Header : HeroAdapterItem()
        object Footer : HeroAdapterItem()
    }
}
