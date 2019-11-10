package com.spitaliere.data.features.characters.repository

import com.spitaliere.data.features.characters.datasource.local.CharacterLocalDataSource
import com.spitaliere.data.features.characters.datasource.remote.CharacterRemoteDataSource
import com.spitaliere.data.features.characters.entity.CharacterCache
import com.spitaliere.data.features.characters.entity.mapCharacterToDomain
import io.mockk.every
import io.mockk.mockkClass
import io.mockk.spyk
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

/**
 * Created by Rafael Spitaliere on 10/11/2019.
 */
class CharacterRepositoryTest {

    private val remoteDataSource: CharacterRemoteDataSource = mockkClass(CharacterRemoteDataSource::class)
    private val localDataSource: CharacterLocalDataSource = mockkClass(CharacterLocalDataSource::class)
    private val spyk = spyk(CharacterRepositoryImpl(remoteDataSource, localDataSource))
    private val listCharacters = listOf(CharacterCache("1001", "", "", "", listOf()))

    @Before
    fun setUp() {
    }

    @Test
    fun `test character repository`() {
        val mapCharacterToDomain = listCharacters.mapCharacterToDomain()
        every { spyk.getCharacters("1") }.returns(Single.just(mapCharacterToDomain))

        val test = spyk.getCharacters("1").test()

        test.assertValue(mapCharacterToDomain)
    }

}