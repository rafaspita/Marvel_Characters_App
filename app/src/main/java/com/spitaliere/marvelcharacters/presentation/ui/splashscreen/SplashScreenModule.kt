package com.spitaliere.marvelcharacters.presentation.ui.splashscreen

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Rafael Spitaliere on 10/11/2019.
 **/
object SplashScreenModule{

    fun getModule() = module {
        viewModel { SplashScreenViewModel()  }
    }
}