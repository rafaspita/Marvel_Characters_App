package com.spitaliere.data.features.comics.datasource.local

import com.spitaliere.data.features.comics.dao.ComicsDao
import com.spitaliere.data.features.comics.entity.ComicsCache
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 03/11/2019.
 **/
class ComicsLocalDataSourceImpl(private val comicsDao: ComicsDao) : ComicsLocalDataSource {

    override fun saveCharacterComics(comicsCache: ComicsCache) = comicsDao.insert(comicsCache)

    override fun getComicsByCharacterId(characterId: String): Single<ComicsCache> = comicsDao.getComicsByCharacterId(characterId)
}