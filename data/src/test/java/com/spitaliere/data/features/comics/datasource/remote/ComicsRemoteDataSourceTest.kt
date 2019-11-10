package com.spitaliere.data.features.comics.datasource.remote

import com.spitaliere.data.api.MarvelApi
import com.spitaliere.data.api.response.*
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
class ComicsRemoteDataSourceTest {

    private val marvelApi = mockkClass(MarvelApi::class)
    private val comicsRemoteDataSource = ComicsRemoteDataSourceImpl(marvelApi)
    private val comicsResult = ComicsResults("", "", "", "", "", "", "", "", Thumbnail("", ""))
    private val data = Data("", "", "", "", listOf(comicsResult))
    private val apiResponse = ApiResponse("", "", "", "", "", data, "")

    @Before
    fun setUp() {
    }

    @Test
    fun `test get comics from remote`() {
        every { marvelApi.getComics("1") }.returns(Single.just(apiResponse))

        val test = comicsRemoteDataSource.getComicsByCharacterId("1").test()

        verify { marvelApi.getComics("1") }
        test.assertValue(ComicsCache("1", apiResponse.data.results.mapComicsToCache()))
    }
}