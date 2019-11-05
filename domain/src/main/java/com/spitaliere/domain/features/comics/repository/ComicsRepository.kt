package com.spitaliere.domain.features.comics.repository

import com.spitaliere.domain.features.comics.model.ComicsInfo
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 03/11/2019.
 **/
interface ComicsRepository {

    fun getCharacterComicsById(characterId: String) : Single<List<ComicsInfo>>

}