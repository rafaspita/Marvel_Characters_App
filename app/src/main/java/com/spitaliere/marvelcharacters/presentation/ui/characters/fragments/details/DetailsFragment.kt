package com.spitaliere.marvelcharacters.presentation.ui.characters.fragments.details

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.spitaliere.domain.features.characters.model.CharacterInfo
import com.spitaliere.domain.features.comics.model.ComicsInfo
import com.spitaliere.marvelcharacters.R
import com.spitaliere.marvelcharacters.databinding.FragmentDetailsBinding
import com.spitaliere.marvelcharacters.presentation.platform.base.BaseFragment
import com.spitaliere.marvelcharacters.presentation.platform.extension.assemble
import com.spitaliere.marvelcharacters.presentation.platform.extension.setImage
import com.spitaliere.marvelcharacters.presentation.platform.extension.visible
import com.spitaliere.marvelcharacters.presentation.ui.characters.fragments.details.dialog.ComicDialog
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : BaseFragment<DetailsViewModel, FragmentDetailsBinding>() {

    override val viewModel: DetailsViewModel by viewModel()
    private val comicsAdapter by inject<ComicsAdapter>()
    private val comicDialog by inject<ComicDialog>()

    override fun getLayout(): Int = R.layout.fragment_details

    override fun registerObservables() {
        viewModel.heroComics.observe(this, Observer { state ->
            state.observeState(
                success = { comicsAdapter.list = it },
                failed = {
                    binding.error.errorText.text = it?.message
                    binding.error.errorContainer.visible()
                }
            )
        })
    }

    override fun setupView(root: View?) {
        val hero = arguments?.getSerializable(HERO) as CharacterInfo
        (activity as AppCompatActivity).supportActionBar?.title = ""
        binding.item = hero
        binding.comics.text = getString(R.string.comics, hero.comics.size)
        comicsAdapter.list = hero.comics.map { ComicsInfo() }

        viewModel.getHeroComics(hero.id)

        with(binding){
            heroeImage.setImage(hero.thumbnail)
            activity?.apply { recyclerViewComics.assemble(this, comicsAdapter, true)  }
            error.tryAgain.setOnClickListener {
                viewModel.getHeroComics(hero.id)
                binding.error.errorContainer.visible(false)
            }
        }

        comicsAdapter.clickItem = {item, view ->
            activity?.apply { comicDialog.show(this, item) }
        }
    }

    companion object{
        const val HERO : String  = "HERO"
    }
}
