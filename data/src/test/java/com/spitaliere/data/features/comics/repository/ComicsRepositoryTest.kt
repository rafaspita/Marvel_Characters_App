package com.spitaliere.data.features.comics.repository

import com.spitaliere.data.features.comics.datasource.local.ComicsLocalDataSource
import com.spitaliere.data.features.comics.datasource.local.ComicsLocalDataSourceImpl
import com.spitaliere.data.features.comics.datasource.remote.ComicsRemoteDataSource
import com.spitaliere.data.features.comics.datasource.remote.ComicsRemoteDataSourceImpl
import com.spitaliere.data.features.comics.entity.ComicsInfoCache
import com.spitaliere.data.features.comics.entity.mapComicsToDomain
import io.mockk.every
import io.mockk.mockkClass
import io.mockk.spyk
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

/**
 * Created by Rafael Spitaliere on 10/11/2019.
 */
class ComicsRepositoryTest {

    private val remoteDataSource: ComicsRemoteDataSource = mockkClass(ComicsRemoteDataSourceImpl::class)
    private val localDataSource: ComicsLocalDataSource = mockkClass(ComicsLocalDataSourceImpl::class)
    private val repositorySpyk = spyk(ComicsRepositoryImpl(remoteDataSource, localDataSource))
    private val comicsInfo = listOf(ComicsInfoCache("", "", "", "", "", "", "", "", ""))

    @Before
    fun setUp() {
    }

    @Test
    fun getCharacterComicsById() {
        val mapComicsToDomain = comicsInfo.mapComicsToDomain()

        every { repositorySpyk.getCharacterComicsById("1") }.returns(Single.just(mapComicsToDomain))

        val test = repositorySpyk.getCharacterComicsById("1").test()

        test.assertValue(mapComicsToDomain)
    }
}