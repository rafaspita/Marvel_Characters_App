package com.spitaliere.data.features.characters.di

import com.spitaliere.data.features.characters.datasource.local.CharacterLocalDataSource
import com.spitaliere.data.features.characters.datasource.local.CharacterLocalDataSourceImpl
import com.spitaliere.data.features.characters.datasource.remote.CharacterRemoteDataSource
import com.spitaliere.data.features.characters.datasource.remote.CharacterRemoteDataSourceImpl
import com.spitaliere.data.features.characters.repository.CharacterRepositoryImpl
import com.spitaliere.data.platform.database.AppDataBase
import com.spitaliere.domain.features.characters.repository.CharacterRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Rafael Spitaliere on 02/11/2019.
 **/
object CharacterModule {

    fun getModule() = module {
        single { AppDataBase.createDataBase(androidContext()).characterDao() }
        single<CharacterRemoteDataSource>{ CharacterRemoteDataSourceImpl(marvelApi = get()) }
        single<CharacterLocalDataSource>{ CharacterLocalDataSourceImpl(characterDao = get()) }
        single<CharacterRepository>{ CharacterRepositoryImpl(remoteDataSource = get(), localDataSource = get()) }
    }
}