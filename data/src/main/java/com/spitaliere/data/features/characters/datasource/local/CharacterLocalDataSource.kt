package com.spitaliere.data.features.characters.datasource.local

import com.spitaliere.data.features.characters.entity.CharacterCache
import com.spitaliere.data.features.characters.entity.OffSetCache
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 02/11/2019.
 **/
interface CharacterLocalDataSource {

    fun saveCharacters(characters: List<CharacterCache>)

    fun saveOffSet(offSetCache: OffSetCache)

    fun getCharactersByOffset(offSet: String) : Single<List<CharacterCache>>

}