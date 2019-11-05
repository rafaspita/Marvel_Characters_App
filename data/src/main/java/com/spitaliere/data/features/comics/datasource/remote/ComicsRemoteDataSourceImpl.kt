package com.spitaliere.data.features.comics.datasource.remote

import com.spitaliere.data.api.MarvelApi
import com.spitaliere.data.api.response.mapComicsToCache
import com.spitaliere.data.features.comics.entity.ComicsCache
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 03/11/2019.
 **/
class ComicsRemoteDataSourceImpl(
    private val marvelApi: MarvelApi
) :
    ComicsRemoteDataSource {

    override fun getComicsByCharacterId(characterId: String): Single<ComicsCache> = marvelApi.getComics(
        characterId = characterId
    ).map { ComicsCache(
        characterId = characterId,
        comicsInfo = it.data.results.mapComicsToCache()
    ) }


}