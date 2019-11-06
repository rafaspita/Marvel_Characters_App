package com.spitaliere.marvelcharacters.presentation.ui.characters

import android.util.Log
import com.spitaliere.marvelcharacters.R
import com.spitaliere.marvelcharacters.databinding.ActivityCharactersBinding
import com.spitaliere.marvelcharacters.presentation.platform.base.BaseActivity
import com.spitaliere.marvelcharacters.presentation.platform.extension.assemble
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersActivity : BaseActivity<CharactersViewModel, ActivityCharactersBinding>() {

    override val viewModel: CharactersViewModel by viewModel()
    private val characterPagedAdapter by inject<CharacterPagedAdapter>()

    override fun getLayout(): Int = R.layout.activity_characters

    override fun showBackButton(): Boolean = false

    override fun getToolbarTitle(): String = getString(R.string.app_name)

    override fun registerObservables() {
        with(viewModel){
            characterPageList
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onError = {it.printStackTrace()},
                    onNext = {characterPagedAdapter.submitList(it)},
                    onComplete = {}
                )

        }
    }

    override fun setupView() {
        binding.recyclerView.assemble(this, characterPagedAdapter)
        characterPagedAdapter.clickListener = { Log.i("Heroe:", it.name) }

    }

}
