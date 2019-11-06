package com.spitaliere.marvelcharacters.presentation.ui.characters

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Created by Rafael Spitaliere on 05/11/2019.
 **/
object CharactersPresentationModule {

    fun getModule() = module {
        viewModel { CharactersViewModel(dataSourceFactory = get())  }
        scope(named<CharactersActivity>()) {
            single { CharacterPagedAdapter() }
        }
    }

}