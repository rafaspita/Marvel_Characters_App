package com.spitaliere.marvelcharacters.presentation.ui.characters.fragments.details

import com.spitaliere.marvelcharacters.presentation.ui.characters.fragments.details.dialog.ComicDialog
import com.spitaliere.marvelcharacters.presentation.ui.characters.fragments.details.dialog.ComicDialogImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Rafael Spitaliere on 07/11/2019.
 **/
object DetailsModule {

    fun getModule() = module {
        viewModel { DetailsViewModel(comicsByIdCase = get())  }
        single<ComicDialog> { ComicDialogImpl() }
    }
}