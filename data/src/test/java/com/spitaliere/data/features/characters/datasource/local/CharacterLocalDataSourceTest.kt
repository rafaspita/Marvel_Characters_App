package com.spitaliere.data.features.characters.datasource.local

import com.spitaliere.data.features.characters.dao.CharacterDao
import com.spitaliere.data.features.characters.dao.OffSetDao
import com.spitaliere.data.features.characters.entity.CharacterCache
import com.spitaliere.data.features.characters.entity.OffSetCache
import io.mockk.every
import io.mockk.mockkClass
import io.mockk.verify
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by Rafael Spitaliere on 10/11/2019.
 */
class CharacterLocalDataSourceTest{

    private val characterDao: CharacterDao = mockkClass(CharacterDao::class)
    private val offsetDao: OffSetDao = mockkClass(OffSetDao::class)
    private val characterLocalDataSource : CharacterLocalDataSource = CharacterLocalDataSourceImpl(characterDao, offsetDao)
    private val listCharacters = listOf(CharacterCache("1001", "", "", "", listOf()))
    private val listString = listOf("1001")
    private val offset = OffSetCache("0", listString)


    @Before
    fun setUp() {
    }

    @Test
    fun `test save character local data source`() {
        every { characterDao.insert(listCharacters) }.returns(Unit)

        val test = characterLocalDataSource.saveCharacters(listCharacters)

        verify { characterDao.insert(listCharacters) }
        assertEquals(Unit, test)
    }

    @Test
    fun `test save offset`() {
        every { offsetDao.insert(offset) }.returns(Unit)

        val test = characterLocalDataSource.saveOffSet(offset)

        verify { offsetDao.insert(offset) }
        assertEquals(Unit, test)
    }

    @Test
    fun `test get characters by offset`() {
        every { offsetDao.getOffSet("0") }.returns(Single.just(offset))
        every { characterDao.getCharactersIn(listString) }.returns(Single.just(listCharacters))

        val test = characterLocalDataSource.getCharactersByOffset("0").test()

        verify { offsetDao.getOffSet("0") }
        verify { characterDao.getCharactersIn(listString) }
        test.assertValue(listCharacters)
    }
}