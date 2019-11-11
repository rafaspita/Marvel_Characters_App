package com.spitaliere.domain.features.comics.usecases

import com.spitaliere.domain.features.comics.model.ComicsInfo
import com.spitaliere.domain.features.comics.repository.ComicsRepository
import io.mockk.every
import io.mockk.mockkClass
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

/**
 * Created by Rafael Spitaliere on 10/11/2019.
 */
class GetCharacterComicsByIdCaseTest {

    private val comicsRepository: ComicsRepository = mockkClass(ComicsRepository::class)
    private val getCharacterComicsByIdCase = GetCharacterComicsByIdCase(comicsRepository)
    private val comics = listOf(ComicsInfo())

    @Before
    fun setUp() {
    }

    @Test
    fun run() {
        every { comicsRepository.getCharacterComicsById("1") }.returns(Single.just(comics))

        val test = getCharacterComicsByIdCase.run("1").test()

        verify { comicsRepository.getCharacterComicsById("1") }

        test.assertValue(comics)
    }
}