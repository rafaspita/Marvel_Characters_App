package com.spitaliere.domain.features.comics.di

import com.spitaliere.domain.features.comics.usecases.GetCharacterComicsByIdCase
import org.koin.dsl.module

/**
 * Created by Rafael Spitaliere on 03/11/2019.
 **/
object ComicsDomainModule {

    fun getModule() = module {
        factory { GetCharacterComicsByIdCase(comicsRepository = get()) }
    }
}