package com.spitaliere.data.features.characters.datasource.remote

import com.spitaliere.data.api.MarvelApi
import com.spitaliere.data.api.response.mapCharactersToCache
import com.spitaliere.data.features.characters.entity.CharacterCache
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 02/11/2019.
 **/
class CharacterRemoteDataSourceImpl(
    private val marvelApi: MarvelApi
) : CharacterRemoteDataSource {

    override fun getCharacters(
        offset: String
    ): Single<List<CharacterCache>> = marvelApi.getCharacters(
        offset = offset
    ).map { it.data.results.mapCharactersToCache() }
}