package com.png.interview.ui.heroes

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.png.interview.api.models.heroes.Hero
import com.png.interview.databinding.DataboundViewHolder
import com.png.interview.databinding.ViewHeroItemBinding
import com.png.interview.databinding.onTypeWithData
import com.png.interview.extensions.getLayoutInflater
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject
import javax.inject.Provider

@FlowPreview
@ExperimentalCoroutinesApi
class HeroesAdapter
@Inject constructor(
    private val presenters: Provider<HeroesItemPresenter>
) : RecyclerView.Adapter<DataboundViewHolder<ViewDataBinding>>() {

    var data = listOf<HeroAdapterItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataboundViewHolder<ViewDataBinding> {
        return DataboundViewHolder(
            when (viewType) {
                TYPE_HERO -> ViewHeroItemBinding.inflate(parent.getLayoutInflater(), parent, false).apply {
                    presenter = presenters.get()
                }
                else -> throw IllegalStateException("Unknown type $viewType")
            }
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: DataboundViewHolder<ViewDataBinding>, position: Int) {
        holder.onTypeWithData<ViewHeroItemBinding, HeroAdapterItem.HeroItem>(data, position) { binding, model ->
            binding.presenter?.bind(model.hero)
        }
        holder.binding.invalidateAll()
    }

    override fun getItemViewType(position: Int) =
        when (data[position]) {
            is HeroAdapterItem.HeroItem -> TYPE_HERO
        }

    fun bindData(heroes: List<Hero>) {
        this.data =
            heroes.map { HeroAdapterItem.HeroItem(it) }
        notifyDataSetChanged()
    }

    sealed class HeroAdapterItem {
        data class HeroItem(val hero: Hero) : HeroAdapterItem()
    }

    companion object {
        private const val TYPE_HERO = 1
    }
}