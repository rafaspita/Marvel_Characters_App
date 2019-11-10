package com.spitaliere.data.features.comics.datasource.local

import com.spitaliere.data.features.comics.dao.ComicsDao
import com.spitaliere.data.features.comics.entity.ComicsCache
import io.mockk.every
import io.mockk.mockkClass
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

/**
 * Created by Rafael Spitaliere on 10/11/2019.
 */
class ComicsLocalDataSourceTest {

    private val comicsDao: ComicsDao = mockkClass(ComicsDao::class)
    private val comicsLocalDataSource = ComicsLocalDataSourceImpl(comicsDao)
    private val comicsCache: ComicsCache = ComicsCache("1", listOf())

    @Before
    fun setUp() {
    }

    @Test
    fun `test save comics cache`() {
        every { comicsDao.insert(comicsCache) }.returns(Unit)

        comicsLocalDataSource.saveCharacterComics(comicsCache)

        verify { comicsDao.insert(comicsCache) }
    }

    @Test
    fun `test get comics by character id`() {
        every { comicsDao.getComicsByCharacterId("1") }.returns(Single.just(comicsCache))

        val test = comicsLocalDataSource.getComicsByCharacterId("1").test()

        verify { comicsDao.getComicsByCharacterId("1") }

        test.assertValue(comicsCache)
    }
}