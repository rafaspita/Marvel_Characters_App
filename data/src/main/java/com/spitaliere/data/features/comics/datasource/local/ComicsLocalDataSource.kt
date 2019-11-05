package com.spitaliere.data.features.comics.datasource.local

import com.spitaliere.data.features.comics.entity.ComicsCache
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 03/11/2019.
 **/
interface ComicsLocalDataSource {

    fun saveCharacterComics(comicsCache: ComicsCache)

    fun getComicsByCharacterId(characterId:String) : Single<ComicsCache>
}