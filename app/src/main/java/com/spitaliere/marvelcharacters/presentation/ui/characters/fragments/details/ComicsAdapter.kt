package com.spitaliere.marvelcharacters.presentation.ui.characters.fragments.details

import com.spitaliere.domain.features.comics.model.ComicsInfo
import com.spitaliere.marvelcharacters.R
import com.spitaliere.marvelcharacters.databinding.ItemComicsBinding
import com.spitaliere.marvelcharacters.presentation.platform.base.BaseRecyclerViewAdapter
import com.spitaliere.marvelcharacters.presentation.platform.extension.setImage
import com.spitaliere.marvelcharacters.presentation.platform.extension.visible

/**
 * Created by Rafael Spitaliere on 05/11/2019.
 **/
class ComicsAdapter : BaseRecyclerViewAdapter<ComicsInfo, ItemComicsBinding>() {

    override fun getLayout(): Int = R.layout.item_comics

    override fun prepareView(item: ComicsInfo, binding: ItemComicsBinding, position: Int) {
        if (item.thumbnail.isBlank()) return

        binding.heroImage.setImage(
            item.thumbnail,
            horizontal = false,
            onSuccess = {binding.progressBar3.visible(false)}
        )

        binding.root.setOnClickListener { clickItem(item, binding) }
    }
}