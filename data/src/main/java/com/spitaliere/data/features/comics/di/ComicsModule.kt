package com.spitaliere.data.features.comics.di

import com.spitaliere.data.features.comics.datasource.local.ComicsLocalDataSource
import com.spitaliere.data.features.comics.datasource.local.ComicsLocalDataSourceImpl
import com.spitaliere.data.features.comics.datasource.remote.ComicsRemoteDataSource
import com.spitaliere.data.features.comics.datasource.remote.ComicsRemoteDataSourceImpl
import com.spitaliere.data.features.comics.repository.ComicsRepositoryImpl
import com.spitaliere.data.platform.database.AppDataBase
import com.spitaliere.domain.features.comics.repository.ComicsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Rafael Spitaliere on 03/11/2019.
 **/
object ComicsModule {

    fun getModule() = module {
        single { AppDataBase.createDataBase(androidContext()).comicsDao() }
        single<ComicsRemoteDataSource>{ ComicsRemoteDataSourceImpl(marvelApi = get()) }
        single<ComicsLocalDataSource>{ ComicsLocalDataSourceImpl(comicsDao = get()) }
        single<ComicsRepository>{ComicsRepositoryImpl(remoteDataSource = get(), localDataSource = get())}
    }
}