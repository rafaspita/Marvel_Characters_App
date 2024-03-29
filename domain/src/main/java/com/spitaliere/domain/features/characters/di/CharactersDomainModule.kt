package com.spitaliere.domain.features.characters.di

import com.spitaliere.domain.features.characters.paging.CharacterDataSourceFactory
import com.spitaliere.domain.features.characters.usecase.RetryUseCase
import org.koin.dsl.module

/**
 * Created by Rafael Spitaliere on 05/11/2019.
 **/
object CharactersDomainModule {

    fun getModule() = module {
        single { CharacterDataSourceFactory(characterRepository = get()) }
        single { RetryUseCase() }
    }
}