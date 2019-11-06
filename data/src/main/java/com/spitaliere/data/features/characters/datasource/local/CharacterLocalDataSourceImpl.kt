package com.spitaliere.data.features.characters.datasource.local

import com.spitaliere.data.features.characters.dao.CharacterDao
import com.spitaliere.data.features.characters.dao.OffSetDao
import com.spitaliere.data.features.characters.entity.CharacterCache
import com.spitaliere.data.features.characters.entity.OffSetCache
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 02/11/2019.
 **/
class CharacterLocalDataSourceImpl(
    private val characterDao: CharacterDao,
    private val offsetDao: OffSetDao
) : CharacterLocalDataSource {

    override fun saveCharacters(characters: List<CharacterCache>) = characterDao.insert(characters)

    override fun saveOffSet(offSetCache: OffSetCache) = offsetDao.insert(offSetCache)

    override fun getCharactersByOffset(offSet: String): Single<List<CharacterCache>> =
        offsetDao.getOffSet(offSet)
            .flatMap { characterDao.getCharactersIn(it.charactersId) }
}