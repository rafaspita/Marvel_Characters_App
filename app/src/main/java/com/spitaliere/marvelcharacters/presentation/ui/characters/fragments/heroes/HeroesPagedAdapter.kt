package com.spitaliere.marvelcharacters.presentation.ui.characters.fragments.heroes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.spitaliere.domain.features.characters.model.CharacterInfo
import com.spitaliere.marvelcharacters.R
import com.spitaliere.marvelcharacters.databinding.ItemCharacterBinding
import com.spitaliere.marvelcharacters.presentation.platform.extension.setImage

/**
 * Created by Rafael Spitaliere on 06/11/2019.
 **/
class HeroesPagedAdapter : PagedListAdapter<CharacterInfo, HeroesPagedAdapter.HeroesViewHolder>(diffCallback) {

    lateinit var clickListener : (characterInfo: CharacterInfo) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        return HeroesViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_character, parent, false))
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.setVariable(BR.item, item)

        item?.let { info ->
            holder.binding.root.setOnClickListener { clickListener(info) }
            holder.binding.heroImage.setImage(info.thumbnail)
        }

    }

    class HeroesViewHolder(val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<CharacterInfo>() {
            override fun areItemsTheSame(oldItem: CharacterInfo, newItem: CharacterInfo): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CharacterInfo, newItem: CharacterInfo): Boolean =
                oldItem.name == newItem.name
        }
    }

}