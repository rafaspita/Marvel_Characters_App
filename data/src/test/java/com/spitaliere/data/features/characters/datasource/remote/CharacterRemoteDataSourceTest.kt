package com.spitaliere.data.features.characters.datasource.remote

import com.spitaliere.data.api.MarvelApi
import com.spitaliere.data.api.response.*
import io.mockk.every
import io.mockk.mockkClass
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

/**
 * Created by Rafael Spitaliere on 10/11/2019.
 */
class CharacterRemoteDataSourceTest {

    private val marvelApi: MarvelApi = mockkClass(MarvelApi::class)
    private val characterRemoteDataSource = CharacterRemoteDataSourceImpl(marvelApi)
    private val characterResult = CharacterResult("", "", "", Thumbnail("", ""), Comics("", "", "", listOf()))
    private val data = Data("", "", "", "", listOf(characterResult))
    private val apiResponse = ApiResponse("", "", "", "", "", data, "")


    @Before
    fun setUp() {
    }

    @Test
    fun `test get characters from remote`() {
        every { marvelApi.getCharacters("1") }.returns(Single.just(apiResponse))

        val test = characterRemoteDataSource.getCharacters("1").test()

        verify { marvelApi.getCharacters("1") }
        test.assertValue(apiResponse.data.results.mapCharactersToCache())
    }
}