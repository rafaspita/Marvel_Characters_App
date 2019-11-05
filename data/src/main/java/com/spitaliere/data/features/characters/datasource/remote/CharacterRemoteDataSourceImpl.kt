package com.spitaliere.data.features.characters.datasource.remote

import com.spitaliere.data.api.MarvelApi
import com.spitaliere.data.api.response.ApiResponse
import com.spitaliere.data.api.response.CharacterResult
import com.spitaliere.data.api.response.Data
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 02/11/2019.
 **/
class CharacterRemoteDataSourceImpl(
    private val marvelApi: MarvelApi
) : CharacterRemoteDataSource {

    override fun getCharacters(
        limit: String,
        offset: String
    ): Single<ApiResponse<Data<CharacterResult>>> = marvelApi.getCharacters(
        limit = limit,
        offset = offset
    )
}