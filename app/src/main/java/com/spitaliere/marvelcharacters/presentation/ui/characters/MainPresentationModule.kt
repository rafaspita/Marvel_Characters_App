package com.spitaliere.marvelcharacters.presentation.ui.characters

import com.spitaliere.marvelcharacters.presentation.ui.characters.fragments.heroes.HeroesPagedAdapter
import com.spitaliere.marvelcharacters.presentation.ui.characters.fragments.details.ComicsAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Created by Rafael Spitaliere on 05/11/2019.
 **/
object MainPresentationModule {

    fun getModule() = module {
        viewModel { MainViewModel(dataSourceFactory = get(), retryUsecase = get())  }
        scope(named<MainActivity>()) {
            single{ HeroesPagedAdapter() }
            factory { ComicsAdapter() }
        }
    }

}