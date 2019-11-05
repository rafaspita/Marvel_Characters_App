package com.spitaliere.data.features.characters.datasource.remote

import com.spitaliere.data.api.response.ApiResponse
import com.spitaliere.data.api.response.CharacterResult
import com.spitaliere.data.api.response.ComicsResults
import com.spitaliere.data.api.response.Data
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 02/11/2019.
 **/
interface CharacterRemoteDataSource {

    fun getCharacters(
       limit: String,
       offset: String
    ): Single<ApiResponse<Data<CharacterResult>>>

}