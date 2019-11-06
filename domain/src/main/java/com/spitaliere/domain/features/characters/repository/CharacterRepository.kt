package com.spitaliere.domain.features.characters.repository

import com.spitaliere.domain.features.characters.model.CharacterInfo
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 02/11/2019.
 **/
interface CharacterRepository {

    fun getCharacters(offset: String): Single<List<CharacterInfo>>
}